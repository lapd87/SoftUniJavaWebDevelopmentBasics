package panda.web.beans;

import org.modelmapper.ModelMapper;
import panda.domain.entities.Status;
import panda.domain.models.service.UserServiceModel;
import panda.domain.models.view.PackageViewModel;
import panda.service.interfaces.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static panda.constants.Constants.INDEX_URL;
import static panda.constants.Constants.PARAMETER_USERNAME;

@Named
@RequestScoped
public class HomeBean {

    private List<PackageViewModel> pendingPackages;
    private List<PackageViewModel> shippedPackages;
    private List<PackageViewModel> deliveredPackages;

    private UserService userService;
    private ModelMapper modelMapper;

    public HomeBean() {
    }

    @Inject
    public HomeBean(UserService userService,
                    ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void initPackages() throws IOException {

        ExternalContext externalContext = FacesContext
                .getCurrentInstance()
                .getExternalContext();

        HttpSession session = (HttpSession) externalContext
                .getSession(false); //TODO check

        String username = (String) session.getAttribute(PARAMETER_USERNAME);

        Optional optionalUser = this.userService
                .findUserByUsername(username);

        if (optionalUser.isEmpty()) {
            externalContext.redirect(INDEX_URL);
        }

        UserServiceModel userServiceModel = (UserServiceModel) optionalUser.get();

        this.pendingPackages = this.getPackagesByStatus(userServiceModel, Status.PENDING.name());

        this.shippedPackages = this.getPackagesByStatus(userServiceModel, Status.SHIPPED.name());

        this.deliveredPackages = this.getPackagesByStatus(userServiceModel, Status.DELIVERED.name());
    }

    public List<PackageViewModel> getPendingPackages() {
        return pendingPackages;
    }

    public void setPendingPackages(List<PackageViewModel> pendingPackages) {
        this.pendingPackages = pendingPackages;
    }

    public List<PackageViewModel> getShippedPackages() {
        return shippedPackages;
    }

    public void setShippedPackages(List<PackageViewModel> shippedPackages) {
        this.shippedPackages = shippedPackages;
    }

    public List<PackageViewModel> getDeliveredPackages() {
        return deliveredPackages;
    }

    public void setDeliveredPackages(List<PackageViewModel> deliveredPackages) {
        this.deliveredPackages = deliveredPackages;
    }

    private List<PackageViewModel> getPackagesByStatus(UserServiceModel userServiceModel, String statusName) {
        return userServiceModel
                .getPackages()
                .stream()
                .filter(p -> p.getStatus().name().equals(statusName))
                .map(p -> this.modelMapper.map(p, PackageViewModel.class))
                .collect(Collectors.toList());
    }
}
