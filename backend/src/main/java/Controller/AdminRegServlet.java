package Controller;

import DAO.UserDAO;
import Models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static View.UtilMethods.*;

public class AdminRegServlet extends HttpServlet {

    UserDAO userDAO;

    public AdminRegServlet(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {


        User userJson = getGsonBuilderExpose().fromJson(request.getReader(), User.class);

        String email = userJson.getEmail();
        String password = userJson.getPassword();
        String firstName = userJson.getFirstName();
        String lastName = userJson.getLastName();

        email = email.toLowerCase();

        if (userDAO.getUserByEmail(email)!=null) {
//            response.setContentType("text/html;charset=utf-8");
//            response.getWriter().println("Имя занято");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        if (password.length() < 3) {
//            response.setContentType("text/html;charset=utf-8");
//            response.getWriter().println("Пароль короче 3х символов");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        userDAO.save(new User(email, password, firstName, lastName));
//        response.setContentType("text/html;charset=utf-8");
//        response.getWriter().println("Вы зарегистрированы");
//        response.getWriter().println(" <br />");
//        response.getWriter().println("<a href=\"/api/v1/dashboard\"> Назад </a>");
        response.setStatus(HttpServletResponse.SC_OK);



    }
}
