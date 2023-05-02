package model;

import java.util.Date;

public class PreparationSheet {
    private int number;
    private Date date;
    private double quantity;

    private String posology;
    private String creation;
    private String attachment;
    private int detail;
    private String preration;

    public PreparationSheet(int number, Date date, double quantity, String posology, String creation, String attachment, int detail, String preration) {
        this.number = number;
        this.date = date;
        this.quantity = quantity;
        this.posology = posology;
        this.creation = creation;
        this.attachment = attachment;
        this.detail = detail;
        this.preration = preration;
    }

    public PreparationSheet(int number, double quantity, String posology, String creation, String attachment, int detail, String preration) {
        this.number = number;
        this.quantity = quantity;
        this.posology = posology;
        this.creation = creation;
        this.attachment = attachment;
        this.detail = detail;
        this.preration = preration;
    }

    public PreparationSheet(int number, Date date, double quantity, String posology,String attachment, int detail, String preration) {
        this.number = number;
        this.date = date;
        this.quantity = quantity;
        this.posology = posology;
        this.attachment = attachment;
        this.detail = detail;
        this.preration = preration;
    }
}
