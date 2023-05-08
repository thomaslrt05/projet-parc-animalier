package model;

public class Fonction {
    private String id;
    private EnumRank rank;
    private String label;

    public Fonction(String id, EnumRank rank, String label) {
        this.id = id;
        this.rank = rank;
        this.label = label;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return  label;
    }
}
