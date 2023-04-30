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

    public static String[] getSpeciesList(){
        String[] speciesList = {"crocodile", "lion", "girafe", "zèbre", "éléphant", "singe", "hippopotame", "puma", "rhinocéros", "tigre"};
        return speciesList;
    }
}
