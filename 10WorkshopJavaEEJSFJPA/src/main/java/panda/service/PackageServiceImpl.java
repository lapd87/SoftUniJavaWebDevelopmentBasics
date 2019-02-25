package panda.service;

import org.modelmapper.ModelMapper;
import panda.domain.entities.Package;
import panda.domain.entities.Status;
import panda.domain.models.service.PackageServiceModel;
import panda.repository.interfaces.PackageRepository;
import panda.service.interfaces.PackageService;

import javax.inject.Inject;
import javax.persistence.RollbackException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static panda.constants.Constants.*;

public class PackageServiceImpl implements PackageService {

    private final PackageRepository packageRepository;
    private final ModelMapper modelMapper;

    @Inject
    public PackageServiceImpl(PackageRepository packageRepository, ModelMapper modelMapper) {
        this.packageRepository = packageRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean packageCreate(PackageServiceModel packageServiceModel) {
        Package aPackage = this.modelMapper.map(packageServiceModel, Package.class);

        this.changeStatus(aPackage);
        this.changeDeliveryDate(aPackage);

        if (aPackage.getDescription().isEmpty()
                || aPackage.getWeight() == null
                || aPackage.getWeight() < 0
                || aPackage.getShippingAddress().isEmpty()
                || aPackage.getStatus() == null
                || aPackage.getEstimatedDeliveryTime().isEmpty()
                || aPackage.getRecipient() == null) {
            return false;
        }

        try {
            this.packageRepository.save(aPackage);
            return true;
        } catch (RollbackException rbe) {
            return false;
        }
    }

    @SuppressWarnings(WARNINGS_UNCHECKED)
    @Override
    public List<PackageServiceModel> findAllPackagesByStatus(Status status) {
        Optional optionalPackages = this.packageRepository
                .findAllPackagesByStatus(status);

        if (optionalPackages.isEmpty()) {
            return new ArrayList<>();
        }

        List<Package> packagesByStatus = (List<Package>) optionalPackages.get();

        return packagesByStatus.stream()
                .map(p -> this.modelMapper.map(p, PackageServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean packageStatusChange(String id) {
        Optional<PackageServiceModel> packageById = this.findPackageById(id);

        if (packageById.isEmpty()) {
            return false;
        }

        Package aPackage = this.modelMapper
                .map(packageById.get(), Package.class);

        this.changeStatus(aPackage);
        this.changeDeliveryDate(aPackage);

        try {
            this.packageRepository.update(aPackage);
            return true;
        } catch (RollbackException rbe) {
            return false;
        }
    }

    @Override
    public Optional<PackageServiceModel> findPackageById(String id) {
        Optional packageById = this.packageRepository.findById(id);

        if (packageById.isEmpty()) {
            return Optional.empty();
        }

        return Optional.ofNullable(this.modelMapper
                .map(packageById.get(), PackageServiceModel.class));
    }

    private void changeStatus(Package aPackage) {

        List<Status> statuses = Arrays.asList(Status.values());
        int currentStatusIndex = statuses.indexOf(aPackage.getStatus());

        int newStatusIndex;

        if (currentStatusIndex < 0) {
            newStatusIndex = 0;
        } else if (currentStatusIndex >= statuses.size() - 1) {
            newStatusIndex = statuses.size() - 1;
        } else {
            newStatusIndex = currentStatusIndex + 1;
        }

        aPackage.setStatus(statuses.get(newStatusIndex));
    }

    private void changeDeliveryDate(Package aPackage) {

        Status packageStatus = aPackage.getStatus();

        if (packageStatus == Status.PENDING) {

            aPackage.setEstimatedDeliveryTime(INITIAL_EDT);

        } else if (packageStatus == Status.SHIPPED) {

            int days = ThreadLocalRandom.current()
                    .nextInt(MIN_EDT_TIME, MAX_EDT_TIME + 1);

            LocalDateTime estimatedDeliveryTime = LocalDateTime.now()
                    .plusDays(days);

            String EDT = estimatedDeliveryTime.format(DATE_TIME_FORMATTER);

            aPackage.setEstimatedDeliveryTime(EDT);

        } else {

            aPackage.setEstimatedDeliveryTime(FINAL_EDT);

        }
    }
}
