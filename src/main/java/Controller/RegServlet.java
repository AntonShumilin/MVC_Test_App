package Controller;

import Models.User;
import Models.UsersMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegServlet extends HttpServlet {

    private UsersMap usersMap;

    public RegServlet(UsersMap usersMap) {
        this.usersMap = usersMap;
    }
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println("страница регистрации");
        response.setStatus(HttpServletResponse.SC_OK);

    }


    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        email = email.toLowerCase();

        if (usersMap.getUserByLogin(login)!=null) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Имя занято");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        if (password.length() < 3) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Пароль короче 3х символов");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        if ((!email.contains("@")) || (!email.contains("."))) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Неверный email");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        usersMap.addNewUser(new User(login, password, firstName, lastName, email));
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println("Вы зарегистрированы");
        response.setStatus(HttpServletResponse.SC_OK);



        }






    }


