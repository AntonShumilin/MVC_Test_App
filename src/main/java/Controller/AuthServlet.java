package Controller;

import DAO.UserDAO;
import Models.User;
import Models.UsersMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthServlet extends HttpServlet {

    UserDAO userDAO;

    public AuthServlet(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || password == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        User user = userDAO.getUserByEmail(email);
        if (user == null || !user.getPassword().equals(password)) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Неверные имя пользователяпароль");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        userDAO.addSession(request.getSession().getId(), user);

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_FOUND);
        response.sendRedirect("/api/v1/dashboard.html");

    }


}
