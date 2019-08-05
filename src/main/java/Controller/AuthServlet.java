package Controller;

import Models.User;
import Models.UsersMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthServlet extends HttpServlet {

    private UsersMap usersMap;

    public AuthServlet(UsersMap usersMap) {
        this.usersMap = usersMap;
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login == null || password == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        User user = usersMap.getUserByLogin(login);
        if (user == null || !user.getPassword().equals(password)) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        usersMap.addSession(request.getSession().getId(), user);

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_FOUND);
        response.sendRedirect("/api/v1/dashboard.html");

    }


}
