package panda.web.beans;

import org.modelmapper.ModelMapper;
import panda.domain.models.binding.PackageCreateBindingModel;
import panda.domain.models.service.PackageServiceModel;
import panda.domain.models.service.UserServiceModel;
import panda.service.interfaces.PackageService;
import panda.service.interfaces.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static panda.constants.Constants.HOME_URL;

@Named
@RequestScoped
public class PackageCreateBean {

    private List<String> users;
    private PackageCreateBindingModel packageCreateBindingModel;

    private PackageService packageService;
    private UserService userService;
    private ModelMapper modelMapper;

    public PackageCreateBean() {
    }

    @Inject
    public PackageCreateBean(PackageService packageService, UserService userService, ModelMapper modelMapper) {
        this.packageService = packageService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init() {
        this.users = this.userService.findAllUsers()
                .stream()
                .map(UserServiceModel::getUsername)
                .collect(Collectors.toList());

        this.packageCreateBindingModel = new PackageCreateBindingModel();
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    public PackageCreateBindingModel getPackageCreateBindingModel() {
        return packageCreateBindingModel;
    }

    public void setPackageCreateBindingModel(PackageCreateBindingModel packageCreateBindingModel) {
        this.packageCreateBindingModel = packageCreateBindingModel;
    }

    public void createPackage() throws IOException {
        Optional optionalRecipient = this.userService
                .findUserByUsername(this.packageCreateBindingModel.getRecipient());

        if (optionalRecipient.isEmpty()) {
            return;
        }

        UserServiceModel recipient = (UserServiceModel) optionalRecipient.get();

        PackageServiceModel packageServiceModel = this.modelMapper
                .map(this.packageCreateBindingModel, PackageServiceModel.class);

        packageServiceModel.setRecipient(recipient);

        boolean isCreated = this.packageService
                .packageCreate(packageServiceModel);

        if (isCreated) {
            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .redirect(HOME_URL);
        }
    }
}
