package model;

import java.util.Date;

public class Treatment {
    private int idCare;
    private Boolean isBasic;
    private String description;
    private String classification;
    private Date date;
    private String animal;

    public Treatment(int idCare, Boolean isBasic, String description, String classification, Date date, String animal) {
        this.idCare = idCare;
        this.isBasic = isBasic;
        this.description = description;
        this.classification = classification;
        this.date = date;
        this.animal = animal;
    }
}
