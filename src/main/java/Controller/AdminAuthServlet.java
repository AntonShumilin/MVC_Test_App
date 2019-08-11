package Controller;

import DAO.UserDAO;
import Models.Check;
import Models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static Main.GetFileFromPageUtil.getFileFromPageUtil;
import static Main.GsonBuilderUtil.getGsonBuilderExpose;

public class AdminAuthServlet extends HttpServlet {

    UserDAO userDAO;

    public AdminAuthServlet(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        User userJson = getGsonBuilderExpose().fromJson(getFileFromPageUtil(request).toString(), User.class);

        String email = userJson.getEmail();
        String password = userJson.getPassword();

        if (email == null || password == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        User user = userDAO.getUserByEmail(email);
        if (user == null || !user.getPassword().equals(password)) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Неверные имя пользователя или пароль");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        userDAO.addSession(request.getSession().getId(), user);

        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println("<a href=\"/api/v1/dashboard\"> Dashboard </a>");
        response.getWriter().println("<br />");
        response.getWriter().println("<a href=\"/logout\"> Logout </a>");
        response.setStatus(HttpServletResponse.SC_OK);

    }
}
