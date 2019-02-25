package panda.web.beans;

import org.modelmapper.ModelMapper;
import panda.domain.entities.Status;
import panda.domain.models.view.PackageViewModel;
import panda.service.interfaces.PackageService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static panda.constants.Constants.PENDING_URL;

@Named
@RequestScoped
public class PendingPackagesBean {

    private List<PackageViewModel> packageViewModels;

    private PackageService packageService;
    private ModelMapper modelMapper;

    public PendingPackagesBean() {
    }

    @Inject
    public PendingPackagesBean(PackageService packageService, ModelMapper modelMapper) {
        this.packageService = packageService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void initPackages() {
        this.packageViewModels = this.packageService
                .findAllPackagesByStatus(Status.PENDING)
                .stream()
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

    public void changeStatus(String id) throws IOException {
        boolean isChanged = this.packageService.packageStatusChange(id);

        if (isChanged) {
            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .redirect(PENDING_URL);
        }
    }
}
