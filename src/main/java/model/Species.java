package model;

public class Species {
    private String id;
    private String label;

    public Species(String id, String label) {
        this.id = id;
        this.label = label;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label +" ("+id+")";
    }
}
