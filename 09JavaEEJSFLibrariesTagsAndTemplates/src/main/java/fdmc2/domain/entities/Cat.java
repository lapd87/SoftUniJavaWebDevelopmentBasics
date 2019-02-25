package fdmc2.domain.entities;

import fdmc2.domain.entities.enums.Gender;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

import static fdmc2.constants.Constants.*;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 13.2.2019 г.
 * Time: 09:45 ч.
 */
@Entity
@Table(name = CAT_ENTITIES)
public class Cat extends BaseEntity {

    private String name;
    private String breed;
    private String color;
    private Integer age;
    private Gender gender;
    private BigDecimal price;
    private Date addedOn;
    private boolean hasPassport;

    public Cat() {
    }

    @Column(name = PARAMETER_NAME, nullable = false, length = 10)
    @NotNull
    @Size(min = 2, max = 10)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = PARAMETER_BREED, nullable = false, length = 20)
    @NotNull
    @Size(min = 5, max = 20)
    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    @Column(name = PARAMETER_COLOR, nullable = false)
    @NotNull
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Column(name = PARAMETER_AGE, nullable = false)
    @NotNull
    @Min(1)
    @Max(31)
    @Digits(integer = 2, fraction = 0)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = PARAMETER_GENDER, nullable = false)
    @NotNull
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Column(name = PARAMETER_PRICE, nullable = false)
    @NotNull
    @DecimalMin("0.01")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = PARAMETER_ADDED, nullable = false)
    @NotNull
    public Date getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(Date time) {
        this.addedOn = time;
    }

    @Column(name = PARAMETER_PASSPORT, nullable = false)
    @NotNull
    public boolean getHasPassport() {
        return hasPassport;
    }

    public void setHasPassport(boolean hasPassport) {
        this.hasPassport = hasPassport;
    }
}