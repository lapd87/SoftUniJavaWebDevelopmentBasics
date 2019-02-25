package exam.web.beans;

import exam.domain.models.binding.UserLoginBindingModel;
import exam.domain.models.service.UserServiceModel;
import exam.service.interfaces.UserService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

import static exam.constants.Constants.*;

@Named
@RequestScoped
public class UserLoginBean {

    private UserLoginBindingModel userLoginBindingModel;

    private UserService userService;
    private ModelMapper modelMapper;

    public UserLoginBean() {
    }

    @Inject
    public UserLoginBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init() {
        this.userLoginBindingModel = new UserLoginBindingModel();
    }

    public UserLoginBindingModel getUserLoginBindingModel() {
        return userLoginBindingModel;
    }

    public void setUserLoginBindingModel(UserLoginBindingModel userLoginBindingModel) {
        this.userLoginBindingModel = userLoginBindingModel;
    }

    public void loginUser() throws IOException {
        UserServiceModel userServiceModel = this.modelMapper
                .map(this.userLoginBindingModel, UserServiceModel.class);

        Optional<UserServiceModel> optionalUserServiceModel = this.userService
                .userLogin(userServiceModel);

        ExternalContext externalContext = FacesContext.getCurrentInstance()
                .getExternalContext();

        if (optionalUserServiceModel.isEmpty()) {
            externalContext.redirect(LOGIN_URL);
        } else {

            userServiceModel = optionalUserServiceModel.get();

            HttpSession session = (HttpSession) externalContext.getSession(false);

            session.setAttribute(PARAMETER_USERNAME, userServiceModel.getUsername());

            externalContext.redirect(HOME_URL);
        }
    }
}
