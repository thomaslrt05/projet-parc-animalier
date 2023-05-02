package model;

import java.util.Date;

public class PrescriptionSheet {
    private int number;
    private Date date;
    private int prescription;
    private String author;

    public PrescriptionSheet(int number, Date date, int prescription, String author) {
        this.number = number;
        this.date = date;
        this.prescription = prescription;
        this.author = author;
    }


}
