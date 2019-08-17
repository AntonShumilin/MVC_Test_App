package Controller;

import DAO.UserDAO;
import Models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    UserDAO userDAO;

    public LogoutServlet(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void doPost(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        String session = request.getSession().getId();
        User user = userDAO.getUserBySession(session);
        userDAO.removeSession(session, user);
        response.setStatus(HttpServletResponse.SC_OK);
        //response.sendRedirect("/api/v1/dashboard");
    }
}
