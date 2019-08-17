package View;

import DAO.ReceiptDAO;
import DAO.UserDAO;
import Main.CheckAuthUtil;
import Models.Item;
import Models.Receipt;
import Models.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static Main.GsonBuilderUtil.getGsonBuilder;
import static Main.GsonBuilderUtil.getGsonBuilderExpose;

public class ViewAllProductsByUserServlet extends HttpServlet {

    UserDAO userDAO;
    ReceiptDAO receiptDAO;

    public ViewAllProductsByUserServlet(UserDAO userDAO, ReceiptDAO receiptDAO) {
        this.userDAO = userDAO;
        this.receiptDAO = receiptDAO;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        if (CheckAuthUtil.checkAuthUtil(userDAO,request,response)) return;

        User user = userDAO.getUserBySession(request.getSession().getId());

        List<Receipt> checkView = receiptDAO.findAllReceiptsByUserId(user.getId());

        response.setContentType("application/json");
        for (Receipt receipt: checkView) {
            for (Item item : receipt.items) {
                response.getWriter().println(getGsonBuilderExpose().toJson(item));
            }
        }
        response.setStatus(HttpServletResponse.SC_OK);

    }
}
