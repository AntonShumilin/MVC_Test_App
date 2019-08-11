package Controller;

import DAO.UserDAO;
import Models.User;

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
    public void doGet(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        User user = userDAO.getUserBySession(request.getSession().getId());
        if (user==null) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("<!DOCTYPE html>");
            response.getWriter().println("<head>");
            response.getWriter().println("<meta charset=\"UTF-8\"/>");
            response.getWriter().println("<title>TestServer</title>");
            response.getWriter().println("</head>");
            response.getWriter().println("<body>");
            response.getWriter().println("<p>Авторизация</p>");
            response.getWriter().println("<form action=\"/\" method=\"POST\">");
            response.getWriter().println("Login: <input type=\"text\" name=\"email\"/>");
            response.getWriter().println("Password: <input type=\"password\" name=\"password\"/>");
            response.getWriter().println("<input type=\"submit\" value=\"Sign in\">");
            response.getWriter().println("</form>");
            response.getWriter().println("<br />");
            response.getWriter().println("<a href=\"/reg\"> Регистрация нового пользователя </a>");
            response.getWriter().println("</body>");
            response.getWriter().println("</html>");
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("<a href=\"/api/v1/dashboard\"> Dashboard </a>");
            response.getWriter().println("<br />");
            response.getWriter().println("<a href=\"/logout\"> Logout </a>");
            response.setStatus(HttpServletResponse.SC_OK);
        }

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
