package model;

public enum Gender {
    M("Male"), F("Female");

    private String label;

    private Gender(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
