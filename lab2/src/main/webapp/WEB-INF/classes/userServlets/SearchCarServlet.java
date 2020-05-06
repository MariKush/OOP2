package userServlets;

import DataBase.DBConnection;
import objects.Car;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet("/searchCar")
public class SearchCarServlet extends HttpServlet {

    boolean correctInput(String startDate, String endDate, int minPrice, int maxPrice, List<String> carTypes) {
        try {
            Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
            Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
            if (date1.compareTo(date2) >= 0) return false;

        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
        if (minPrice > maxPrice) return false;

        return !carTypes.isEmpty();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<String> parameterNamesList = Collections.list(req.getParameterNames());

        if (!parameterNamesList.contains("startDay") || !parameterNamesList.contains("endDay") ||
                !parameterNamesList.contains("minValue") || !parameterNamesList.contains("maxValue")) {
            resp.sendRedirect("orderCar");
            return;
        }
        String startDay = req.getParameter("startDay");
        String endDay = req.getParameter("endDay");
        int minValue = Integer.parseInt(req.getParameter("minValue"));
        int maxValue = Integer.parseInt(req.getParameter("maxValue"));
        String[] carTypes = req.getParameterValues("carType");


        List<Car> cars = DBConnection.getCars(startDay, endDay, minValue, maxValue, carTypes);
        req.setAttribute("Cars", cars.toArray(new Car[0]));
        getServletContext().getRequestDispatcher("/userPages/searchCar.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getParameter("startDay"));
        if (req.getParameter("id") == null || req.getParameter("startDay") == null || req.getParameter("endDay") == null) {
            resp.sendRedirect("orderCar");
            return;
        }
        String startDay = req.getParameter("startDay");
        String endDay = req.getParameter("endDay");
        int id = Integer.parseInt(req.getParameter("id"));
        System.out.println(id);

        resp.sendRedirect("confirmOrder?id=" + id + "&startDay=" + startDay + "&endDay=" + endDay);

    }
}
