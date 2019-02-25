package employees.web.beans;

import employees.domain.models.binding.EmployeeRegisterBindingModel;
import employees.domain.models.service.EmployeeServiceModel;
import employees.service.interfacces.EmployeeService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import java.io.IOException;

import static employees.constants.Constants.INDEX_URL;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 13.2.2019 г.
 * Time: 11:43 ч.
 */
@Named
@RequestScoped
public class EmployeeRegisterBean {

    private EmployeeRegisterBindingModel employeeRegisterBindingModel;

    private EmployeeService employeeService;
    private ModelMapper modelMapper;

    public EmployeeRegisterBean() {
        this.employeeRegisterBindingModel = new EmployeeRegisterBindingModel();
    }

    @Inject
    public EmployeeRegisterBean(EmployeeService employeeService, ModelMapper modelMapper) {
        this();
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
    }

    public EmployeeRegisterBindingModel getEmployeeRegisterBindingModel() {
        return employeeRegisterBindingModel;
    }

    public void setEmployeeRegisterBindingModel(EmployeeRegisterBindingModel employeeRegisterBindingModel) {
        this.employeeRegisterBindingModel = employeeRegisterBindingModel;
    }

    public void register() throws IOException {
        EmployeeServiceModel employeeServiceModel = this.modelMapper
                .map(this.employeeRegisterBindingModel, EmployeeServiceModel.class);

        boolean isRegistered = this.employeeService
                .registerEmployee(employeeServiceModel);

        if (isRegistered) {
            ExternalContext externalContext = FacesContext.getCurrentInstance()
                    .getExternalContext();

            externalContext.redirect(INDEX_URL);
        }
    }
}