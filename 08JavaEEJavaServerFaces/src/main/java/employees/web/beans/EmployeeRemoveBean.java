package employees.web.beans;

import employees.service.interfacces.EmployeeService;

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
 * Time: 12:49 ч.
 */
@Named
@RequestScoped
public class EmployeeRemoveBean {

    private EmployeeService employeeService;

    public EmployeeRemoveBean() {
    }

    @Inject
    public EmployeeRemoveBean(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void remove(String id) throws IOException {

        this.employeeService.removeEmployeeById(id);

        ExternalContext externalContext = FacesContext.getCurrentInstance()
                .getExternalContext();

        externalContext.redirect(INDEX_URL);
    }
}