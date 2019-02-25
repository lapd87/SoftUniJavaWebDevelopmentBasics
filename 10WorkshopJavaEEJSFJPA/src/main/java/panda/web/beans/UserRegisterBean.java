package panda.web.beans;

import org.modelmapper.ModelMapper;
import panda.domain.models.binding.UserRegisterBindingModel;
import panda.domain.models.service.UserServiceModel;
import panda.service.interfaces.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

import static panda.constants.Constants.LOGIN_URL;

@Named
@RequestScoped
public class UserRegisterBean {

    private UserRegisterBindingModel userRegisterBindingModel;

    private UserService userService;
    private ModelMapper modelMapper;

    public UserRegisterBean() {
    }

    @Inject
    public UserRegisterBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init() {
        this.userRegisterBindingModel = new UserRegisterBindingModel();
    }

    public UserRegisterBindingModel getUserRegisterBindingModel() {
        return userRegisterBindingModel;
    }

    public void setUserRegisterBindingModel(UserRegisterBindingModel userRegisterBindingModel) {
        this.userRegisterBindingModel = userRegisterBindingModel;
    }

    public void registerUser() throws IOException {
        if (this.userRegisterBindingModel.getPassword()
                .equals(this.userRegisterBindingModel.getConfirmPassword())) {

            UserServiceModel userServiceModel = this.modelMapper
                    .map(this.userRegisterBindingModel, UserServiceModel.class);

            boolean isRegistered = this.userService.userRegister(userServiceModel);

            if (isRegistered) {
                FacesContext.getCurrentInstance()
                        .getExternalContext()
                        .redirect(LOGIN_URL);
            }
        }
    }
}
