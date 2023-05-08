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
    private String preparation;

    public PreparationSheet(int number, Date date, double quantity, String posology, String creation, String attachment, int detail, String preparation) {
        this.number = number;
        this.date = date;
        this.quantity = quantity;
        this.posology = posology;
        this.creation = creation;
        this.attachment = attachment;
        this.detail = detail;
        this.preparation = preparation;
    }

    public PreparationSheet(int number, double quantity, String posology, String creation, String attachment, int detail, String preparation) {
        this.number = number;
        this.quantity = quantity;
        this.posology = posology;
        this.creation = creation;
        this.attachment = attachment;
        this.detail = detail;
        this.preparation = preparation;
    }

    public PreparationSheet(int number, Date date, double quantity, String posology,String attachment, int detail, String preparation) {
        this.number = number;
        this.date = date;
        this.quantity = quantity;
        this.posology = posology;
        this.attachment = attachment;
        this.detail = detail;
        this.preparation = preparation;
    }

    public int getNumber() {
        return number;
    }

    public Date getDate() {
        return date;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getPosology() {
        return posology;
    }

    public String getCreation() {
        return creation;
    }

    public String getAttachment() {
        return attachment;
    }

    public int getDetail() {
        return detail;
    }

    public String getPreparation() {
        return preparation;
    }

    @Override
    public String toString(){
        return number + " " + date + " " + detail;
    }
}
