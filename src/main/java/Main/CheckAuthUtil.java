package Main;

import DAO.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CheckAuthUtil {

    public static boolean checkAuthUtil (UserDAO userDAO, HttpServletRequest request, HttpServletResponse response) throws IOException {

        if (userDAO.getUserBySession(request.getSession().getId()) == null) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Вы неавторизованы");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return true;
        }
        return false;
    }

    public static boolean checkAdminAuthUtil (UserDAO userDAO, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = userDAO.getAdminBySession(request.getSession().getId());
        if (!Main.config.oauth.adminUsers.contains(email)) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Вы неавторизованы");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return true;
        }
        return false;
    }
}
