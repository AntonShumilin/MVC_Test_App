package View;
import DAO.UserDAO;
import Models.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static View.UtilMethods.*;


public class ViewUserById extends HttpServlet {

    UserDAO userDAO;

    public ViewUserById(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        if (userDAO.checkAdminAuthUtil(userDAO,request,response)) return;

        String pathInfo = request.getPathInfo();
        pathInfo = pathInfo.replace("/","");

        long userId;
        try {
            userId = Integer.parseInt(pathInfo);
        } catch (Exception e) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("косяк");
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        User user = userDAO.findById(userId);

        if (user == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        response.setContentType("application/json");
        response.getWriter().println(getGsonBuilder().toJson(user));
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
