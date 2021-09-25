package com.example.studentmanagement;

public class StudentForm {
    private int id;
    private String name;
    private int birthYear;
    private String address;

    public StudentForm(int id, String name, int birthYear, String address) {
        this.id = id;
        this.name = name;
        this.birthYear = birthYear;
        this.address = address;
    }

    public StudentForm() {

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "StudentForm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthYear=" + birthYear +
                ", address='" + address + '\'' +
                '}';
    }
}
