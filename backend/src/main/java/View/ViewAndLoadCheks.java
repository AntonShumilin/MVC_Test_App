package View;

import DAO.ReceiptDAO;
import DAO.UserDAO;
import Models.*;
import org.hibernate.HibernateException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static View.UtilMethods.*;

public class ViewAndLoadCheks extends HttpServlet {

    UserDAO userDAO;
    ReceiptDAO receiptDAO;

    public ViewAndLoadCheks(UserDAO userDAO, ReceiptDAO receiptDAO) {
        this.userDAO = userDAO;
        this.receiptDAO = receiptDAO;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        if (userDAO.checkAuthUtil(userDAO,request,response)) return;

        User user = userDAO.getUserBySession(request.getSession().getId());

        DateParams dateParams = parseDateParam(request);

        List<Receipt> checkView = receiptDAO.findAllReceiptsWithFilters(user.getId(), dateParams, request.getParameter("merchInn"));

        sortListOfJson(checkView, request.getParameter("sort"));
        sendListOfJson(checkView, request, response);
        }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (userDAO.checkAuthUtil(userDAO,request,response)) return;

        Check check = getGsonBuilderExposeDateFormat().fromJson(request.getReader(), Check.class);

        Receipt receipt = check.document.receipt;
        User user = userDAO.getUserBySession(request.getSession().getId());
        receipt.setUserIDandName(user.getId());
        for (Item it: receipt.items) {
            it.setReceipt(receipt);
            it.dateTime = receipt.dateTime;
        }

        try {
            receiptDAO.save(receipt);
            String json = getGsonBuilderExpose().toJson(receipt);
            response.setContentType("application/json");
            response.getWriter().println(json);
            response.setStatus(HttpServletResponse.SC_OK);
        }
        catch (HibernateException e) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("такой чек уже есть");
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }
}




