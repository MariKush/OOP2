package AdminServlets;

import DataBase.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import objects.Model;

@WebServlet("/addCar")
public class AddCarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String[] carStyles = DBConnection.getCarStyles().toArray(new String[0]);
        Model[] carModels=DBConnection.getCarModels().toArray(new Model[0]);
        req.setAttribute("CarStyles", carStyles);
        req.setAttribute("CarModels", carModels);
        getServletContext().getRequestDispatcher("/adminPages/addCar.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int carModelID = Integer.parseInt(req.getParameter("carModel"));
        String carStyleName = req.getParameter("carStyle");
        int carYearProduction = Integer.parseInt(req.getParameter("yearProduction"));
        int price = Integer.parseInt(req.getParameter("price"));

        if (!DBConnection.addCar(carModelID, carStyleName, carYearProduction, price)) {
            req.getSession().setAttribute("Error", "cant add car");
        }
        resp.sendRedirect("addCar");

    }
}
