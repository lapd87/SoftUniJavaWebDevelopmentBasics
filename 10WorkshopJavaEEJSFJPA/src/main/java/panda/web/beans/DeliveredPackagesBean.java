package panda.web.beans;

import org.modelmapper.ModelMapper;
import panda.domain.entities.Status;
import panda.domain.models.service.PackageServiceModel;
import panda.domain.models.view.PackageViewModel;
import panda.service.interfaces.PackageService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Named
@RequestScoped
public class DeliveredPackagesBean {

    private List<PackageViewModel> packageViewModels;

    private PackageService packageService;
    private ModelMapper modelMapper;

    public DeliveredPackagesBean() {
    }

    @Inject
    public DeliveredPackagesBean(PackageService packageService, ModelMapper modelMapper) {
        this.packageService = packageService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void initPackages() {
        List<PackageServiceModel> deliveredPackages = this.packageService
                .findAllPackagesByStatus(Status.DELIVERED);

        List<PackageServiceModel> acquiredPackages = this.packageService
                .findAllPackagesByStatus(Status.ACQUIRED);

        List<PackageServiceModel> packages = Stream.concat(deliveredPackages.stream(),
                acquiredPackages.stream())
                .collect(Collectors.toList());

        this.packageViewModels = packages.stream()
                .map(p -> {
                    PackageViewModel packageViewModel = this.modelMapper.map(p, PackageViewModel.class);
                    packageViewModel.setRecipient(p.getRecipient().getUsername());
                    return packageViewModel;
                })
                .collect(Collectors.toList());
    }

    public List<PackageViewModel> getPackageViewModels() {
        return packageViewModels;
    }

    public void setPackageViewModels(List<PackageViewModel> packageViewModels) {
        this.packageViewModels = packageViewModels;
    }
}
