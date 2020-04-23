package userServlets;

import DataBase.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/orderCar")
public class OrderCarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        String [] carStyles= DBConnection.getCarStyles().toArray(new String[0]);

        req.setAttribute("CarStyles", carStyles);
        getServletContext().getRequestDispatcher("/userPages/orderCar.jsp").forward(req, resp);
    }

}
