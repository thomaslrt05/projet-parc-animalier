package model;

import java.util.Date;

public class TreatmentByMedicine {
    private double quantity;
    private Date date;
    private String description;

    public TreatmentByMedicine(double quantity, Date date, String description) {
        this.quantity = quantity;
        this.date = date;
        this.description = description;
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
