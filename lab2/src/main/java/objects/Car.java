package objects;

public class Car {
    private int id;
    private int yearProduction;
    String carModelName;
    String carBrandName;
    String carStyleName;
    private long price;

    public Car(int id, int yearProduction, String carModelName, String carBrandName, String carStyleName, long price) {
        this.id = id;
        this.yearProduction = yearProduction;
        this.carModelName = carModelName;
        this.carBrandName = carBrandName;
        this.carStyleName = carStyleName;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public int getYearProduction() {
        return yearProduction;
    }

    public String getCarModelName() {
        return carModelName;
    }

    public String getCarBrandName() {
        return carBrandName;
    }

    public String getCarStyleName() {
        return carStyleName;
    }

    public long getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return carBrandName+' '+carModelName+' '+carStyleName+'\n'+yearProduction;
    }
}
