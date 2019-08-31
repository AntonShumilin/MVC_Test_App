package View;

import DAO.ReceiptDAO;
import DAO.UserDAO;
import Models.DateParams;
import Models.Item;
import Models.Receipt;
import Models.User;
import javafx.print.Collation;
import org.hibernate.mapping.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static View.UtilMethods.*;

public class ViewAllProductsByUserServlet extends HttpServlet {

    UserDAO userDAO;
    ReceiptDAO receiptDAO;

    public ViewAllProductsByUserServlet(UserDAO userDAO, ReceiptDAO receiptDAO) {
        this.userDAO = userDAO;
        this.receiptDAO = receiptDAO;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        if (userDAO.checkAuthUtil(userDAO, request, response)) return;

        User user = userDAO.getUserBySession(request.getSession().getId());

//        List<Receipt> checkView = receiptDAO.findAllReceiptsByUserId(user.getId());

        DateParams dateParams = parseDateParam(request);

        List<Receipt> checkView = receiptDAO.findAllReceiptsWithFilters(user.getId(), dateParams, request.getParameter("merchInn"));

        List<Item> list = new LinkedList<>();

        for (Receipt receipt : checkView) {
                list.addAll(receipt.items);
        }
        sortListOfJson(list, request.getParameter("sort"));
        sendListOfJson(list, request, response);

    }
}
