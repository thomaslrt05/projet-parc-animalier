package model;

public class Breed {
    private String id;
    private String label;
    private String specification;

    public Breed(String id, String label, String specification) {
        this.id = id;
        this.label = label;
        this.specification = specification;
    }

    public String getId() {
        return id;
    }
}
