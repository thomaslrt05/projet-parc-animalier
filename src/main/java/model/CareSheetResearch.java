package model;

import java.util.Date;

public class CareSheetResearch {
    private String label;
    private String code;
    private String name;
    private String labelCaresheet;
    private Date date;

    public CareSheetResearch(String label, String code, String name, String labelCaresheet, Date date) {
        this.label = label;
        this.code = code;
        this.name = name;
        this.labelCaresheet = labelCaresheet;
        this.date = date;
    }

    public String getLabel() {
        return label;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getLabelCaresheet() {
        return labelCaresheet;
    }

    public Date getDate() {
        return date;
    }
}
