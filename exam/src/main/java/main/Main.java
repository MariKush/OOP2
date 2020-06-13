package main;

import dbconnection.DBConnection;
import entitties.Weather;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[]args){
        //first
        System.out.println("Вивести відомості про погоду в заданому регіоні.");
        Scanner in = new Scanner(System.in);
        String region = in.nextLine();
        ArrayList<Weather> weathersStat = DBConnection.getWeatherStatInRegion(region);
        for(Weather w: weathersStat){
            System.out.println(w.toString());
        }

        //second
        System.out.println("Вивести дати, коли в заданому регіоні йшов сніг, і температура була нижче -10.");
        region = in.nextLine();
        ArrayList<java.sql.Date> snowDates = DBConnection.getSnowDatesInRegion(region);
        for(Date d: snowDates){
            System.out.println(d);
        }

        //third
        System.out.println("Вивести інформацію про погоду за минулий тиждень у регіонах, жителі яких спілкуються заданою мовою.");
        String lang = in.nextLine();
        weathersStat = DBConnection.getWeatherStatInRegionsBySpeakingLang(lang);
        for(Weather w: weathersStat){
            System.out.println(w.toString());
        }

        //fourth
        System.out.println("Вивести середню температуру за минулий тиждень у регіонах з площею більше 1000.");
        int temperature = DBConnection.getAverageTemperature();
        System.out.println("Average temperature is : " + temperature);
    }
}
