package model;

public enum Gender {
    M("M"), F("F");

    private String label;

    private Gender(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
