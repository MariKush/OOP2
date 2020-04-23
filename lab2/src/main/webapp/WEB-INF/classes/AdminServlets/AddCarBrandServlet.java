package AdminServlets;

import DataBase.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/addCarBrand")
public class AddCarBrandServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/adminPages/addCarBrand.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String carBrand = req.getParameter("carBrand");
        if (!DBConnection.addCarBrand(carBrand)){
            req.getSession().setAttribute("Error", "cant add brand");
        }
        resp.sendRedirect("addCarBrand");
    }
}
