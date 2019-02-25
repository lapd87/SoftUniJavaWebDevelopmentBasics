package employees.service;

import employees.domain.entities.Employee;
import employees.domain.models.service.EmployeeServiceModel;
import employees.repository.interfaces.EmployeeRepository;
import employees.service.interfacces.EmployeeService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.persistence.RollbackException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static employees.constants.Constants.WARNINGS_UNCHECKED;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @Inject
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean registerEmployee(EmployeeServiceModel employeeServiceModel) {
        Employee employee = this.modelMapper
                .map(employeeServiceModel, Employee.class);

        if (employee.getFirstName().isEmpty()
                || employee.getLastName().isEmpty()
                || employee.getPosition().isEmpty()
                || employee.getSalary() == null
                || employee.getAge() == null) {
            return false;
        }

        try {
            this.employeeRepository.save(employee);
            return true;
        } catch (RollbackException rbe) {
            return false;
        }
    }

    @Override
    public Optional<EmployeeServiceModel> findEmployeeById(String id) {

        Optional employeeById = this.employeeRepository.findById(id);

        if (employeeById.isEmpty()) {
            return Optional.empty();
        }

        return Optional.ofNullable(this.modelMapper
                .map(employeeById.get(), EmployeeServiceModel.class));
    }

    @SuppressWarnings(WARNINGS_UNCHECKED)
    @Override
    public List<EmployeeServiceModel> findAllEmployees() {

        List<Employee> allEmployees = (List<Employee>) this.employeeRepository
                .findAll().orElseGet(ArrayList::new);

        return allEmployees.stream()
                .map(e -> this.modelMapper
                        .map(e, EmployeeServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean removeEmployeeById(String id) {
        Optional<EmployeeServiceModel> employeeById = this.findEmployeeById(id);

        if (employeeById.isEmpty()) {
            return false;
        }

        Employee employee = this.modelMapper
                .map(employeeById.get(), Employee.class);

        try {
            this.employeeRepository.removeById(employee.getId());
            return true;
        } catch (RollbackException rbe) {
            return false;
        }
    }
}
