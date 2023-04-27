package pnevsky.com.models;

import javax.validation.constraints.*;

public class Person {
    private int id;

    @NotEmpty(message = "The field should not be empty")
    @Size(max = 25, message = "The field should be less than 25 characters")
    private String name;



    @Min(value = 1900, message = "The year of birth should not me less then 1900")
    private int birthYear;

    public Person() { }

    public Person(String name, int birthYear) {
        this.name = name;
        this.birthYear = birthYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    @Override
    public String toString() {
        return "id " + id + ", " + name + ", " + birthYear;
    }
}