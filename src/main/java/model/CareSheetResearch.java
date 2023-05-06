package model;

import java.util.Date;

public class CareSheetResearch {
    private String speciesLabel;
    private String code;
    private String name;
    private String breedLabel;
    private Date date;

    public CareSheetResearch(String speciesLabel, String code, String name, String breedLabel, Date date) {
        this.speciesLabel = speciesLabel;
        this.code = code;
        this.name = name;
        this.breedLabel = breedLabel;
        this.date = date;
    }

    public String getSpeciesLabel() {
        return speciesLabel;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getBreedLabel() {
        return breedLabel;
    }

    public Date getDate() {
        return date;
    }
}
