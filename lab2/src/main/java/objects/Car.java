package objects;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Car {
    private int id;
    private int yearProduction;
    String carModelName;
    String carBrandName;
    String carStyleName;
    private long price;

    @Override
    public String toString() {
        return carBrandName+' '+carModelName+' '+carStyleName+'\n'+yearProduction;
    }
}
