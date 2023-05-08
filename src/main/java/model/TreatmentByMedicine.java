package model;

import java.util.Date;

public class TreatmentByMedicine {

    private String description;
    private double quantity;
    private Date date;


    public TreatmentByMedicine(String description, double quantity, Date date) {
        this.description = description;
        this.quantity = quantity;
        this.date = date;
    }

    public double getQuantity() {
        return quantity;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }
}
