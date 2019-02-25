package employees.web.beans;

import employees.domain.models.view.EmployeeListViewModel;
import employees.service.interfacces.EmployeeService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 13.2.2019 г.
 * Time: 12:13 ч.
 */
@Named
@RequestScoped
public class EmployeeListBean {

    private List<EmployeeListViewModel> employeeListViewModels;
    private double totalSalary;
    private double averageSalary;

    private EmployeeService employeeService;
    private ModelMapper modelMapper;

    public EmployeeListBean() {
        this.employeeListViewModels = new ArrayList<>();
    }

    @Inject
    public EmployeeListBean(EmployeeService employeeService, ModelMapper modelMapper) {
        this();
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
        this.employeeListViewModels = this.employeeService
                .findAllEmployees()
                .stream()
                .map(e -> this.modelMapper
                        .map(e, EmployeeListViewModel.class))
                .collect(Collectors.toList());
        this.totalSalary = this.employeeListViewModels.stream()
                .mapToDouble(EmployeeListViewModel::getSalary)
                .sum();
        this.averageSalary = this.employeeListViewModels.size() > 0 ?
                this.totalSalary / this.employeeListViewModels.size() : 0;
    }

    public List<EmployeeListViewModel> getEmployeeListViewModels() {
        return employeeListViewModels;
    }

    public void setEmployeeListViewModels(List<EmployeeListViewModel> employeeListViewModels) {
        this.employeeListViewModels = employeeListViewModels;
    }

    public double getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(double totalSalary) {
        this.totalSalary = totalSalary;
    }

    public double getAverageSalary() {
        return  averageSalary;
    }

    public void setAverageSalary(double averageSalary) {
        this.averageSalary = averageSalary;
    }
}