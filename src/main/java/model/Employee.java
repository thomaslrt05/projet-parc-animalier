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

    public String getAuthor(){
        return this.lastName + " " + this.firstName.charAt(0);
    }


}
