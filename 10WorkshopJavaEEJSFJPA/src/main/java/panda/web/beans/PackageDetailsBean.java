package panda.web.beans;

import org.modelmapper.ModelMapper;
import panda.domain.models.service.PackageServiceModel;
import panda.domain.models.view.PackageViewModel;
import panda.service.interfaces.PackageService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.Optional;

import static panda.constants.Constants.INDEX_URL;

@Named
@RequestScoped
public class PackageDetailsBean {

    private PackageViewModel packageViewModel;

    private PackageService packageService;
    private ModelMapper modelMapper;

    public PackageDetailsBean() {
    }

    @Inject
    public PackageDetailsBean(PackageService packageService,
                              ModelMapper modelMapper) {
        this.packageService = packageService;
        this.modelMapper = modelMapper;
    }

    public PackageViewModel getPackageViewModel() {
        return packageViewModel;
    }

    public void setPackageViewModel(PackageViewModel packageViewModel) {
        this.packageViewModel = packageViewModel;
    }

    public void getPackage(String id) throws IOException {
        Optional<PackageServiceModel> packageById = this.packageService.findPackageById(id);

        if (packageById.isEmpty()) {
            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .redirect(INDEX_URL);
        } else {
            PackageServiceModel packageServiceModel = packageById.get();

            PackageViewModel packageViewModel = this.modelMapper
                    .map(packageServiceModel, PackageViewModel.class);

            packageViewModel.setRecipient(packageServiceModel.getRecipient().getUsername());

            this.packageViewModel = packageViewModel;
        }
    }
}
