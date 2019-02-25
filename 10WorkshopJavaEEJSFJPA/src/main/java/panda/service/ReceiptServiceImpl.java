package panda.service;

import org.modelmapper.ModelMapper;
import panda.domain.entities.Package;
import panda.domain.entities.Receipt;
import panda.domain.entities.User;
import panda.domain.models.service.PackageServiceModel;
import panda.domain.models.service.ReceiptServiceModel;
import panda.repository.interfaces.ReceiptRepository;
import panda.service.interfaces.PackageService;
import panda.service.interfaces.ReceiptService;
import panda.service.interfaces.UserService;

import javax.inject.Inject;
import javax.persistence.RollbackException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static panda.constants.Constants.RECEIPT_FEE_MULTIPLIER;
import static panda.constants.Constants.WARNINGS_UNCHECKED;

public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final PackageService packageService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Inject
    public ReceiptServiceImpl(ReceiptRepository receiptRepository,
                              PackageService packageService,
                              UserService userService,
                              ModelMapper modelMapper) {
        this.receiptRepository = receiptRepository;
        this.packageService = packageService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean receiptCreate(String packageId, String username) {

        Optional userByUsername = this.userService
                .findUserByUsername(username);

        boolean isChanged = this.packageService
                .packageStatusChange(packageId);

        if (userByUsername.isEmpty()
                || !isChanged) {
            return false;
        }


        Optional<PackageServiceModel> packageById = this.packageService
                .findPackageById(packageId);

        if (packageById.isEmpty()) {
            return false;
        }

        Package aPackage = this.modelMapper
                .map(packageById.get(), Package.class);

        User user = this.modelMapper
                .map(userByUsername.get(), User.class);


        Receipt receipt = new Receipt();

        receipt.setFee(new BigDecimal(RECEIPT_FEE_MULTIPLIER
                * aPackage.getWeight()));
        receipt.setIssuedOn(LocalDateTime.now());
        receipt.setRecipient(user);
        receipt.setaPackage(aPackage);

        if (receipt.getFee() == null
                || receipt.getFee().compareTo(new BigDecimal(0)) < 0
                || receipt.getIssuedOn() == null
                || receipt.getIssuedOn().compareTo(LocalDateTime.now()) > 0
                || receipt.getRecipient() == null
                || receipt.getaPackage() == null) {
            return false;
        }

        try {
            this.receiptRepository.save(receipt);
            return true;
        } catch (RollbackException rbe) {
            return false;
        }
    }

    @SuppressWarnings(WARNINGS_UNCHECKED)
    @Override
    public List<ReceiptServiceModel> findAllReceiptsByUsername(String username) {

        Optional userByUsername = this.userService
                .findUserByUsername(username);

        if (userByUsername.isEmpty()) {
            return new ArrayList<>();
        }

        User user = this.modelMapper
                .map(userByUsername.get(),User.class);

        Optional optionalReceipts = this.receiptRepository
                .findAllReceiptsByUser(user);
        //TODO

        if (optionalReceipts.isEmpty()) {
            return new ArrayList<>();
        }

        List<Receipt> receiptsByUser = (List<Receipt>) optionalReceipts.get();

        return receiptsByUser.stream()
                .map(r -> this.modelMapper.map(r, ReceiptServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ReceiptServiceModel> findReceiptById(String id) {
        Optional receiptById = this.receiptRepository.findById(id);

        if (receiptById.isEmpty()) {
            return Optional.empty();
        }

        return Optional.ofNullable(this.modelMapper
                .map(receiptById.get(), ReceiptServiceModel.class));
    }
}
