package View;

import DAO.ReceiptDAO;
import DAO.UserDAO;
import Main.CheckAuthUtil;
import Main.GetFileFromPageUtil;
import Models.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.HibernateException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static Main.GetFileFromPageUtil.getFileFromPageUtil;
import static Main.GsonBuilderUtil.getGsonBuilderExpose;

public class ViewAndLoadCheks extends HttpServlet {

    UserDAO userDAO;
    ReceiptDAO receiptDAO;

    public ViewAndLoadCheks(UserDAO userDAO, ReceiptDAO receiptDAO) {
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
            response.getWriter().println(getGsonBuilderExpose().toJson(receipt));
        }
        response.setStatus(HttpServletResponse.SC_OK);
        }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (CheckAuthUtil.checkAuthUtil(userDAO,request,response)) return;

        Check check = getGsonBuilderExpose().fromJson(getFileFromPageUtil(request).toString(), Check.class);

        Receipt receipt = check.document.receipt;
        User user = userDAO.getUserBySession(request.getSession().getId());
        receipt.setUserID(user.getId());
        for (Item it: receipt.items) {
            it.setReceipt(receipt);
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




