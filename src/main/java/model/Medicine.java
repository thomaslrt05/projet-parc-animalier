package model;

public class Medicine {
    private String name;
    private String unitOfMeasurement;
    private String instruction;

    public Medicine(String name, String unitOfMeasurement, String instruction) {
        this.name = name;
        this.unitOfMeasurement = unitOfMeasurement;
        this.instruction = instruction;
    }

    @Override
    public String toString(){
        return name;
    }

    public String getId() {
        return name;
    }
}
