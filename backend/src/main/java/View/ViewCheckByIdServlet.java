package View;

import DAO.ReceiptDAO;
import DAO.UserDAO;
import Models.Receipt;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static View.UtilMethods.*;


public class ViewCheckByIdServlet extends HttpServlet {

    UserDAO userDAO;
    ReceiptDAO receiptDAO;

    public ViewCheckByIdServlet(UserDAO userDAO, ReceiptDAO receiptDAO) {
        this.userDAO = userDAO;
        this.receiptDAO = receiptDAO;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        if (userDAO.checkAuthUtil(userDAO,request,response)) return;

        String pathInfo = request.getPathInfo();
        pathInfo = pathInfo.replace("/","");


        long checkId;
        try {
            checkId = Integer.parseInt(pathInfo);
        } catch (Exception e) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("косяк");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Receipt receipt = receiptDAO.findReceiptkById(checkId);
        if (receipt == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        response.setContentType("application/json");
        response.getWriter().println(getGsonBuilderExpose().toJson(receipt));
        response.setStatus(HttpServletResponse.SC_OK);

    }

}
