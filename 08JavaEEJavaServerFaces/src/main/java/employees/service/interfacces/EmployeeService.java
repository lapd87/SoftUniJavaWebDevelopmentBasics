package employees.service.interfacces;

import employees.domain.models.service.EmployeeServiceModel;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    boolean registerEmployee(EmployeeServiceModel employeeServiceModel);

    Optional<EmployeeServiceModel> findEmployeeById(String id);

    List<EmployeeServiceModel> findAllEmployees();

    boolean removeEmployeeById(String id);
}
