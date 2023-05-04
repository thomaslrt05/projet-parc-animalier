package model;

public class RemarkByFonction {
    private String lastName;
    private String firstName;
    private String label;
    private String description;

    public RemarkByFonction(String lastName, String firstName, String label, String description) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.label = label;
        this.description = description;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLabel() {
        return label;
    }

    public String getDescription() {
        return description;
    }
}
