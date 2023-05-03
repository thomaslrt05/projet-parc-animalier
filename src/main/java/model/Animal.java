package model;

import java.util.Date;

public class Animal {
    private String code;
    private String name;
    private Gender sex;
    private Boolean isDangerous;
    private double weight;
    private Date arrivalDate;
    private String nickName;
    private String breed;

    public Animal(String code, String name, Gender sex, Boolean isDangerous, double weight,String breed) {
        this.code = code;
        this.name = name;
        this.sex = sex;
        this.isDangerous = isDangerous;
        this.weight = weight;
        this.breed = breed;
        this.arrivalDate = new Date();
    }


    public Animal(String code, String name, Gender sex, Boolean isDangerous, double weight,String breed, String nickName) {
        this.code = code;
        this.name = name;
        this.sex = sex;
        this.isDangerous = isDangerous;
        this.weight = weight;
        this.nickName = nickName;
        this.breed = breed;
        this.arrivalDate = new Date();
    }

    public String toString(){
        return this.code + " " + this.name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Gender getSex() {
        return sex;
    }

    public Boolean getDangerous() {
        return isDangerous;
    }

    public double getWeight() {
        return weight;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public String getNickName() {
        return nickName;
    }

    public String getBreed() {
        return breed;
    }
}
