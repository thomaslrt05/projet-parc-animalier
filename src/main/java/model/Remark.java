package model;

import java.util.Date;

public class Remark {
    private Date date;
    private String animal;
    private int number;
    private String description;
    private Boolean isMadeByVeterinian;
    private String author;

    public Remark(Date date, String animal, int number, String description, Boolean isMadeByVeterinian, String author) {
        this.date = date;
        this.animal = animal;
        this.number = number;
        this.description = description;
        this.isMadeByVeterinian = isMadeByVeterinian;
        this.author = author;
    }


}
