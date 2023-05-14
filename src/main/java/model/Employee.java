package model;

public class Employee {
    private String matricule;
    private String lastName;
    private String firstName;
    private String supervisor;
    private String position;

    public Employee(String matricule, String lastName, String firstName, String supervisor, String position) {
        this.matricule = matricule;
        this.lastName = lastName;
        this.firstName = firstName;
        this.supervisor = supervisor;
        this.position = position;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public String toString(){
        return lastName + " " + firstName.charAt(0) + ".  (" + matricule + " )";
    }
}
