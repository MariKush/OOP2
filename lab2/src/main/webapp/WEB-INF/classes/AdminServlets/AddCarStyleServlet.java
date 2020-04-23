package AdminServlets;

import DataBase.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addCarStyle")
public class AddCarStyleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/adminPages/addCarStyle.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String carStyle= req.getParameter("carStyle");
        if (!DBConnection.addCarStyle(carStyle)){
            req.getSession().setAttribute("Error", "cant add style");
        }
        resp.sendRedirect("addCarStyle");
    }
}
