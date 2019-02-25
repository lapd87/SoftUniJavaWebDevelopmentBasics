package employees.domain.models.view;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 13.2.2019 г.
 * Time: 12:14 ч.
 */
public class EmployeeListViewModel {

    private String id;
    private String firstName;
    private String lastName;
    private String position;
    private Double salary;
    private Integer age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}