package AdminServlets;

import DataBase.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addCarModel")
public class AddCarModelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] carBrands = DBConnection.getCarBrands().toArray(new String[0]);
        req.setAttribute("CarBrands", carBrands);
        getServletContext().getRequestDispatcher("/adminPages/addCarModel.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String carBrand = req.getParameter("carBrand");
        String carModel = req.getParameter("carModel");

        if (!DBConnection.addCarModel(carBrand,carModel)){
            req.getSession().setAttribute("Error", "cant add style");
        }
        resp.sendRedirect("addCarModel");
    }
}
