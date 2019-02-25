package employees.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import static employees.constants.Constants.*;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 13.2.2019 г.
 * Time: 09:45 ч.
 */
@Entity
@Table(name = EMPLOYEE_ENTITIES)
public class Employee extends BaseEntity {

    private String firstName;
    private String lastName;
    private String position;
    private Double salary;
    private Integer age;

    @Column(name = PARAMETER_FIRST_NAME, nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = PARAMETER_LAST_NAME, nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = PARAMETER_POSITION, nullable = false)
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Column(name = PARAMETER_SALARY, nullable = false)
    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Column(name = PARAMETER_AGE, nullable = false)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}