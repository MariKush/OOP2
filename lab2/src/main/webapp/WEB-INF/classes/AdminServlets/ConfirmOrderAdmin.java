package AdminServlets;

import DataBase.DBConnection;
import objects.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/confirmOrderAdmin")
public class ConfirmOrderAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("orders", DBConnection.getOrdersToConfirm().toArray(new Order[0]));
        getServletContext().getRequestDispatcher("/adminPages/confirmOrderAdmin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") == null||req.getParameter("type")==null){
            resp.sendRedirect("confirmOrderAdmin");
            return;
        }
        int orderID=Integer.parseInt(req.getParameter("id"));
        String type=req.getParameter("type");
        if(type.equals("confirm")){
            DBConnection.setConfirmed(orderID);
        }
        else {
            DBConnection.setRefused(orderID, req.getParameter("Comment"));
        }
        resp.sendRedirect("confirmOrderAdmin");
    }
}
