import DataBase.DBConnection;

public class Main {
    public static void main(String[] args) {
        //DBConnection.addReport(1, "2020-05-20");

        System.out.println(DBConnection.addProfitPerMonth(1, "2020-05-20", 20));
    }
}
