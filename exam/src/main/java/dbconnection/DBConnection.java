package dbconnection;

import entitties.Region;
import entitties.Weather;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
    private static final String jdbcDriverName = "org.postgresql.Driver";
    private static final String url = "jdbc:postgresql://localhost:5432/weather";
    private static final String user = "postgres";
    private static final String password = "321";
    private static Logger logger = Logger.getLogger(DBConnection.class.getName());

    public static ArrayList<Weather> getWeatherStatInRegion(String name){
        try {
            Class.forName(jdbcDriverName);
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "select * " +
                    "from weather " +
                    "         inner join region on weather.region_id = region.id " +
                    "where region.name = ?;";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1,name);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Weather> weather = new ArrayList<>();
            while (rs.next()) {
                Weather w = new Weather();
                w.setId(rs.getInt(1));
                w.setRegionId(rs.getInt(2));
                w.setDate(rs.getDate(3));
                w.setTemperature(rs.getInt(4));
                w.setPrecipitation(rs.getString(5));
                weather.add(w);
            }
            connection.close();
            return weather;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public static ArrayList<Date> getSnowDatesInRegion(String region){
        try {
            Class.forName(jdbcDriverName);
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "select date " +
                    "from wether" +
                    "   inner join region on weather.region_id = region.id" +
                    "where region.name = ? and weather.precipitation='snow' and weather.temperature < -10;";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Date> dates = new ArrayList<>();
            while (rs.next()) {
                dates.add(rs.getDate(1));
            }
            connection.close();
            return dates;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public static ArrayList<Weather> getWeatherStatInRegionsBySpeakingLang(String lang){
        try {
            Class.forName(jdbcDriverName);
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "select * from weather inner join region r on weather.region_id = r.id inner join persons p on r.person_id = p.id " +
                    "where lang = ? and date >= date_trunc('week',now()-'7 day'::interval);";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1,lang);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Weather> weather = new ArrayList<>();
            while (rs.next()) {
                Weather w = new Weather();
                w.setId(rs.getInt(1));
                w.setRegionId(rs.getInt(2));
                w.setDate(rs.getDate(3));
                w.setTemperature(rs.getInt(4));
                w.setPrecipitation(rs.getString(5));
                weather.add(w);
                weather.add(w);
            }
            connection.close();
            return weather;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }
    public static int getAverageTemperature(){
        try {
            Class.forName(jdbcDriverName);
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "select AVG(temperature) " +
                    "from wether " +
                    "  inner join region on weather.region_id = region.id " +
                    "where region.square > 1000 and date >= date_trunc('week',now()-'7 day'::interval);";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            connection.close();
            return rs.getInt(1);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            return 0;
        }
    }
}
