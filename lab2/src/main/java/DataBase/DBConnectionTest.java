package DataBase;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static DataBase.DBConnection.*;
import static org.junit.jupiter.api.Assertions.*;

class DBConnectionTest {

    private final String carBrand = "carBrand";
    private final String carStyle = "carStyle";
    private final String carModel = "carModel";

    private final int YEAR = 2000;
    private final int PRISE = 200;

    @Test
    public void createConnection() throws SQLException {
        Connection connection = DBConnection.createConnection();
        assertNotNull(connection);
        connection.close();
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

        DBConnection.addCar(carModelID, carStyle, YEAR, PRISE);
        int carID = getCarID(carModelID, carStyleID, YEAR, PRISE);
        assertNotEquals(-1, carID);

        DBConnection.deleteCar(carID);
        carID = getCarID(carModelID, carStyleID, YEAR, PRISE);
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
        DBConnection.addCar(carModelID, carStyle, YEAR, PRISE);
        int carID = getCarID(carModelID, carStyleID, YEAR, PRISE);

        String startDay = "2020-04-03";
        String endDay = "2020-04-05";
        String name = "Mariia";
        String surName = "Kushnirenko";
        String passportID = "1234567890";

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