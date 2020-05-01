package DataBase;

import objects.Car;
import objects.Model;
import objects.Order;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import static java.time.temporal.ChronoUnit.DAYS;

public class DBConnection {

    private static Logger log = Logger.getLogger(DBConnection.class.getName());

    private static Connection createConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "MARIIA", "mariia");
    }

    private static boolean getTransaction(String... sqls) {
        Connection connection = null;
        try {
            connection = createConnection();
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            for (String sql : sqls) {
                statement.executeUpdate(sql);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static int getID(String sql) {
        Connection connection = null;
        try {
            connection = createConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                int res = resultSet.getInt(1);
                connection.close();
                return res;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private static boolean isFree(int carID, String startDate, String endDate) {
        String sql = "SELECT orderID " +
                "FROM orders " +
                "WHERE carID="+carID+" AND NOT ((" +
                "TO_DATE('" + startDate + "', 'yyyy-MM-dd') BETWEEN startDate AND endDate) OR " +
                "(TO_DATE('" + endDate + "', 'yyyy-MM-dd') BETWEEN startDate AND endDate))";
        log.info("Check whether this car is available for this period");
        return getID(sql)!=-1;
    }


    public static List<Car> getCars(String startDate, String endDate, int minPrice, int maxPrice, String[] carTypes) {
        List<Car> cars = new LinkedList<>();

        String sql = "SELECT * " +
                "FROM cars INNER JOIN carModels USING(carModelId) " +
                "INNER JOIN carStyles USING(carStyleId) " +
                "INNER JOIN carBrands USING (carBrandId) " +
                "WHERE (pricePerDay BETWEEN " + minPrice + " AND " + maxPrice + ") AND carStyleName IN( ";
        for (int i = 0; i < carTypes.length; i++) {
            sql += (i != 0 ? ", " : "") + "'" + carTypes[i] + "'";
        }
        sql += ")";
        LocalDate startDateL = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate endDateL = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        long countDay = DAYS.between(startDateL, endDateL);
        Connection connection = null;
        try {
            connection = createConnection();
            Statement statement = connection.createStatement();
            ResultSet carsSet = statement.executeQuery(sql);
            while (carsSet.next()) {
                if (isFree(carsSet.getInt("carID"), startDate,endDate))
                cars.add(new Car(carsSet.getInt("carID"), carsSet.getInt("yearProduction"),
                        carsSet.getString("carModelName"), carsSet.getString("carBrandName"),
                        carsSet.getString("carStyleName"), carsSet.getInt("pricePerDay") * countDay));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    private static List<String> getList(String sql) {
        List<String> listString = new LinkedList<>();

        Connection connection = null;
        try {
            connection = createConnection();
            Statement statement = connection.createStatement();
            ResultSet carStylesSet = statement.executeQuery(sql);
            while (carStylesSet.next()) {
                listString.add(carStylesSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listString;
    }

    public static List<String> getCarStyles() {
        String sql = "SELECT carStyleName FROM carStyles";
        return getList(sql);
    }

    public static List<String> getCarBrands() {
        String sql = "SELECT carBrandName FROM carBrands";
        return getList(sql);
    }

    public static List<Model> getCarModels() {
        List<Model> carModels = new LinkedList<>();
        String sql = "SELECT carModelName, carBrandName, carModelID " +
                "FROM carModels INNER JOIN carBrands USING(carBrandID)";

        Connection connection = null;
        try {
            connection = createConnection();
            Statement statement = connection.createStatement();
            ResultSet carStylesSet = statement.executeQuery(sql);
            while (carStylesSet.next()) {
                carModels.add(new Model(carStylesSet.getString(1),
                        carStylesSet.getString(2), carStylesSet.getInt(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carModels;
    }


    public static int getBrandID(String carBrandName) {
        String sql = "SELECT carBrandID " +
                "FROM carBrands " +
                "WHERE carBrandName= '" + carBrandName + "'";
        return getID(sql);
    }

    public static int getStyleID(String carStyleName) {
        String sql = "SELECT carStyleID " +
                "FROM carStyles " +
                "WHERE carStyleName= '" + carStyleName + "'";
        return getID(sql);
    }

    public static int getModelID(String carModelName) {
        String sql = "SELECT carModelID " +
                "FROM carModels " +
                "WHERE carModelName= '" + carModelName + "'";
        return getID(sql);
    }


    public static boolean addCarBrand(String carBrandName) {
        if (getBrandID(carBrandName) != -1) return false;
        String sql = "INSERT INTO carBrands(carBrandId, carBrandName) " +
                "VALUES (carBrands_carBrandID.nextVal, '" + carBrandName + "')";
        log.info("Add car brand: " + carBrandName);
        return getTransaction(sql);
    }

    public static boolean addCarStyle(String carStyleName) {
        if (getStyleID(carStyleName) != -1) return false;
        String sql = "INSERT INTO carStyles(carStyleId, carStyleName) " +
                "VALUES (carStyles_carStyleID.nextVal, '" + carStyleName + "')";
        log.info("Add car style: " + carStyleName);
        return getTransaction(sql);
    }

    public static boolean addCarModel(String carBrandName, String carModelName) {
        if (getModelID(carModelName) != -1) return false;

        int carBrandID = getBrandID(carBrandName);
        if (carBrandID == -1) return false;

        String sql = "INSERT INTO carModels(carModelID, carModelName, carBrandID) " +
                "VALUES (carStyles_carStyleID.nextVal, '" + carModelName + "', " + carBrandID + " )";
        log.info("Add car model: " + carModelName);
        return getTransaction(sql);
    }

    public static boolean addCar(int carModelID, String carStyleName, int carYearProduction, int price) {
        int carStyleID = getStyleID(carStyleName);
        if (carModelID == -1 || carStyleID == -1) return false;

        String sql = "INSERT INTO cars(carID, carModelID, carStyleID, yearProduction, pricePerDay) " +
                "VALUES (cars_carID.nextVal, " + carModelID + ", " + carStyleID + ", " + carYearProduction + ", " + price + " )";
        //System.out.println(sql);
        log.info("Add car: " + carStyleName + " " + carYearProduction + " " + price);
        return getTransaction(sql);
    }


    public static boolean addOrder(int carID, String startDay, String endDay, String name, String surName, String passportID, String creditCard, String mobileNum) {
        String sql = "INSERT INTO ORDERS (orderID,startDate, endDate, carID, name, surName, passportID, creditCard, mobileNum, state)" +
                "VALUES(orders_orderID.nextVal, TO_DATE('" + startDay + "', 'yyyy-MM-dd'),TO_DATE('" + endDay + "', 'yyyy-MM-dd')," + carID + ", '" + name + "', '" + surName +
                "', '" + passportID + "', '" + creditCard + "', '" + mobileNum + "', 0 )";
        //System.out.println(sql);
        log.info("Add order by " + name + " " + surName + " " + passportID);
        return getTransaction(sql);
    }

    private static List<Order> getOrders(String sql) {

        List<Order> orders = new LinkedList<>();
        Connection connection = null;

        try {
            connection = createConnection();
            Statement statement = connection.createStatement();
            ResultSet orderSet = statement.executeQuery(sql);
            while (orderSet.next()) {
                LocalDate startDate = orderSet.getDate("startDate").toLocalDate();
                LocalDate endDate = orderSet.getDate("endDate").toLocalDate();
                long countDay = DAYS.between(startDate, endDate);
                Car car = new Car(orderSet.getInt("carID"), orderSet.getInt("yearProduction"),
                        orderSet.getString("carModelName"), orderSet.getString("carBrandName"),
                        orderSet.getString("carStyleName"), (orderSet.getInt("pricePerDay") * countDay));
                orders.add(new Order(orderSet.getInt("orderID"), car, orderSet.getString("name"),
                        orderSet.getString("surname"), orderSet.getString("passportID"),
                        orderSet.getString("creditCard"), orderSet.getString("mobileNum")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public static List<Order> getOrdersToConfirm() {
        String sql = "SELECT * FROM orders INNER JOIN cars USING(carID) " +
                "INNER JOIN carModels USING(carModelId) " +
                "INNER JOIN carStyles USING(carStyleId) " +
                "INNER JOIN carBrands USING (carBrandId) " +
                "WHERE state=0";
        return getOrders(sql);
    }

    public static List<Order> getOrderToReturn() {
        String sql = "SELECT * FROM orders INNER JOIN cars USING(carID) " +
                "INNER JOIN carModels USING(carModelId) " +
                "INNER JOIN carStyles USING(carStyleId) " +
                "INNER JOIN carBrands USING (carBrandId) " +
                "WHERE state=2 AND endDate<=current_date";
        return getOrders(sql);
    }


    public static boolean setConfirmed(int id) {
        String sql = "UPDATE ORDERS " +
                "SET state=" + 1 +
                " WHERE orderID=" + id;
        log.info("Order " + id + " confirmed");
        return getTransaction(sql);

    }

    public static boolean setRefused(int id, String comment) {
        String sql = "UPDATE ORDERS " +
                "SET state=" + 9 + ", comm=" + comment +
                " WHERE orderID=" + id;
        log.info("Order " + id + " refused because " + comment);
        return getTransaction(sql);
    }

    public static boolean setReturned(int id) {
        String sql = "UPDATE ORDERS " +
                "SET state=" + 2 +
                " WHERE orderID=" + id;
        log.info("Order " + id + " returned");
        return getTransaction(sql);
    }
}
