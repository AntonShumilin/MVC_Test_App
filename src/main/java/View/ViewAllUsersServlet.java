package View;

import DAO.UserDAO;
import Main.CheckAuthUtil;
import Models.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static Main.GsonBuilderUtil.getGsonBuilderExpose;


public class ViewAllUsersServlet extends HttpServlet {

    UserDAO userDAO;

    public ViewAllUsersServlet(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        if (CheckAuthUtil.checkAuthUtil(userDAO,request,response)) return;

        List<User> usersList = userDAO.findAllUsers();

        response.setContentType("application/json");
        for (User user : usersList) {
            response.getWriter().println(getGsonBuilderExpose().toJson(user));
        }
        response.setStatus(HttpServletResponse.SC_OK);

    }
}
