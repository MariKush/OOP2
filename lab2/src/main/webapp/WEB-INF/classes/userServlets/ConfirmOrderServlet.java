package userServlets;

import DataBase.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/confirmOrder")
public class ConfirmOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/userPages/confirmOrder.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") == null||req.getParameter("startDay")==null||req.getParameter("endDay")==null){
            resp.sendRedirect("orderCar");
            return;
        }
        int carID = Integer.parseInt(req.getParameter("id"));
        String startDay=req.getParameter("startDay");
        String endDay=req.getParameter("endDay");
        String name = req.getParameter("Name");
        String surname = req.getParameter("Surname");
        String passportID = req.getParameter("PassportID");
        String creditCard = req.getParameter("CreditCard");
        String mobileNum = req.getParameter("MobileNum");
        if (!DBConnection.addOrder(carID,startDay,endDay, name, surname, passportID, creditCard, mobileNum)) {
            req.getSession().setAttribute("Error", "cant add order");
        }
        resp.sendRedirect("orderCar");
    }
}
