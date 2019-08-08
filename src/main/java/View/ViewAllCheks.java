package View;

import DAO.UserDAO;
import Models.CheckWeb;
import Models.User;
import Models.UsersMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class ViewAllCheks extends HttpServlet {

    UserDAO userDAO;

    public ViewAllCheks(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        User user = userDAO.getUserBySession(request.getSession().getId());
        ArrayList<String> checkView = new ArrayList<>();
        for (Map.Entry<String, CheckWeb> entry: user.userChecks.entrySet()) {
            String key = entry.getKey();
            checkView.add("Date " + user.getCheckByFS(key).document.receipt.dateTime + " " + "Sum nal " +
                                    user.getCheckByFS(key).document.receipt.cashTotalSum + " " + "Sum beznal " +
                                    user.getCheckByFS(key).document.receipt.ecashTotalSum);
        }
        response.setContentType("text/html;charset=utf-8");
        for (String s : checkView) {
            response.getWriter().println(s);
            response.getWriter().println("<br />");

        }



    }



}
