package AdminServlets;

import DataBase.DBConnection;
import objects.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/confirmReturn")
public class ConfirmReturnServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("orders", DBConnection.getOrderToReturn().toArray(new Order[0]));
        getServletContext().getRequestDispatcher("/adminPages/confirmReturn.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") == null){
            resp.sendRedirect("confirmReturnServlet");
            return;
        }
        int orderID=Integer.parseInt(req.getParameter("id"));
        DBConnection.setReturned(orderID);

        resp.sendRedirect("confirmReturn");

    }
}
