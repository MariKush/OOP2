package AdminServlets;


import DataBase.DBConnection;
import objects.Car;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/statistics")
public class StatisticsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String startDay = req.getParameter("startDay");
        String endDay = req.getParameter("endDay");
        List<Car> cars = DBConnection.getCars();
        for (Car car : cars) {
            car.setPrice(DBConnection.getProfit(car.getId(), startDay, endDay));
        }

        req.setAttribute("cars", cars.toArray(new Car[0]));
        getServletContext().getRequestDispatcher("/adminPages/statistics.jsp").forward(req, resp);
    }
}
