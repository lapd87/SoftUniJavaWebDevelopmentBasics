package fdmc.domain.entities;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 29.1.2019 г.
 * Time: 15:39 ч.
 */
public class Cat {

    private String name;
    private String breed;
    private String color;
    private int age;

    public Cat() {
    }

    public Cat(String name, String breed, String color, int age) {
        this.name = name;
        this.breed = breed;
        this.color = color;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}