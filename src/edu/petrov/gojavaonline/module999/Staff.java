package edu.petrov.gojavaonline.module999;

/**
 * Created by anton on 04/04/16.
 */
public class Staff {
    private String name;
    private int age;
    private String profession;

    public Staff() {
    }

    public Staff(String name, int age, String profession) {
        this.name = name;
        this.age = age;
        this.profession = profession;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", profession='" + profession + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
