package DataBase;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Random;

import static DataBase.DBConnection.*;
import static org.junit.jupiter.api.Assertions.*;

class DBConnectionTest {

    private final String carBrand = generateString();
    private final String carStyle = generateString();
    private final String carModel = generateString();


    private String generateString() {
        byte[] array = new byte[5];
        new Random().nextBytes(array);
        return Base64.getEncoder().encodeToString(array);
    }


    @Test
    public void addDeleteCarBrand() {
        DBConnection.addCarBrand(carBrand);
        assertNotEquals(-1, DBConnection.getBrandID(carBrand));
        DBConnection.deleteCarBrand(carBrand);
        assertEquals(-1, DBConnection.getBrandID(carBrand));
    }

    @Test
    public void addDeleteCarStyle() {
        DBConnection.addCarStyle(carStyle);
        assertNotEquals(-1, DBConnection.getStyleID(carStyle));
        DBConnection.deleteCarStyle(carStyle);
        assertEquals(-1, DBConnection.getStyleID(carStyle));
    }

    @Test
    public void addDeleteCarModel() {
        DBConnection.addCarBrand(carBrand);

        DBConnection.addCarModel(carBrand, carModel);
        assertNotEquals(-1, getModelID(carModel));

        DBConnection.deleteCarModel(carModel);
        assertEquals(-1, getModelID(carModel));

        DBConnection.deleteCarBrand(carBrand);
    }

    @Test
    public void addDeleteCar() {
        DBConnection.addCarStyle(carStyle);
        DBConnection.addCarBrand(carBrand);
        DBConnection.addCarModel(carBrand, carModel);

        int carModelID = getModelID(carModel);
        int carStyleID = getStyleID(carStyle);
        DBConnection.addCar(carModelID, carStyle, 2000, 200);
        int carID = getCarID(carModelID, carStyleID, 2000, 200);
        assertNotEquals(-1, carID);

        DBConnection.deleteCar(carID);
        carID = getCarID(carModelID, carStyleID, 2000, 200);
        assertEquals(-1, carID);

        DBConnection.deleteCarStyle(carStyle);
        DBConnection.deleteCarModel(carModel);
        DBConnection.deleteCarBrand(carBrand);
    }

    @Test
    public void addDeleteOrder() {
        DBConnection.addCarStyle(carStyle);
        DBConnection.addCarBrand(carBrand);
        DBConnection.addCarModel(carBrand, carModel);

        int carModelID = getModelID(carModel);
        int carStyleID = getStyleID(carStyle);
        DBConnection.addCar(carModelID, carStyle, 2000, 200);
        int carID = getCarID(carModelID, carStyleID, 2000, 200);

        String startDay = "2020-04-03";
        String endDay = "2020-04-05";
        String name = "Mariia";
        String surName = "Kushnir";
        String passportID = "2371923";

        DBConnection.addOrder(carID, startDay, endDay, name, surName, passportID, "6125632", "0506909114");

        int orderID = getOrderId(carID, name, surName, passportID);

        assertNotEquals(-1, orderID);

        DBConnection.deleteOrder(orderID);

        orderID = getOrderId(carID, name, surName, passportID);
        assertEquals(-1, orderID);


        DBConnection.deleteCar(carID);
        DBConnection.deleteCarStyle(carStyle);
        DBConnection.deleteCarModel(carModel);
        DBConnection.deleteCarBrand(carBrand);
    }



}