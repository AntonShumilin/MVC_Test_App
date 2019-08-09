package View;

import DAO.CheckDAO;
import DAO.ReceiptDAO;
import DAO.UserDAO;
import Models.Check;
import Models.Receipt;
import Models.User;
import Models.UsersMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ViewAllCheks extends HttpServlet {

    UserDAO userDAO;
    ReceiptDAO receiptDAO;

    public ViewAllCheks(UserDAO userDAO, ReceiptDAO receiptDAO) {
        this.userDAO = userDAO;
        this.receiptDAO = receiptDAO;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        User user = userDAO.getUserBySession(request.getSession().getId());
        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        Gson gson = builder.setPrettyPrinting().create();

        List<Receipt> checkView = receiptDAO.findAllReceiptsByUserId(user.getId());

        response.setContentType("application/json");
        for (Receipt receipt: checkView) {
            response.getWriter().println(gson.toJson(receipt));
        }
        response.setStatus(HttpServletResponse.SC_OK);
        }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long checkId;
        try {
             checkId = Integer.parseInt(request.getParameter("checkId"));
        } catch (Exception e) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("это не цифра");
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }
        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        Gson gson = builder.setPrettyPrinting().create();

        Receipt receipt = receiptDAO.findReceiptkById(checkId);

        response.setContentType("application/json");
        response.getWriter().println(gson.toJson(receipt));
        response.setStatus(HttpServletResponse.SC_OK);


    }

    }




