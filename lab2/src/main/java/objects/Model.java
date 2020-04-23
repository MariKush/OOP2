package objects;

public class Model {
    String carModelName;
    String carBrandName;
    private int modelID;

    public Model(String carModelName, String carBrandName, int modelID) {
        this.carModelName = carModelName;
        this.carBrandName = carBrandName;
        this.modelID = modelID;
    }

    public String getCarModelName() {
        return carModelName;
    }

    public String getCarBrandName() {
        return carBrandName;
    }

    public int getModelID() {
        return modelID;
    }

    @Override
    public String toString() {
        return carBrandName+" "+carModelName;
    }
}
