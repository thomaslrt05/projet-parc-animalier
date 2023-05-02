package model;

import java.util.Date;

public class CareSheetResearch {
    private Date date;
    private String name;
    private String code;
    private String label;
    private String description;

    public CareSheetResearch(Date date, String name, String code, String label, String description) {
        this.date = date;
        this.name = name;
        this.code = code;
        this.label = label;
        this.description = description;
    }
}
