package model;

import java.util.Date;

public class CareSheet {
    private String label;
    private Date date;
    private String animal;

    public CareSheet(String label, Date date, String animal) {
        this.label = label;
        this.date = date;
        this.animal = animal;
    }


}
