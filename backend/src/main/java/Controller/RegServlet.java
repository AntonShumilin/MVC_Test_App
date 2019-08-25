package Controller;

import DAO.UserDAO;
import Models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegServlet extends HttpServlet {

    UserDAO userDAO;

    public RegServlet(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println("<head>");
        response.getWriter().println("<meta charset=\"UTF-8\"/>");
        response.getWriter().println("<title>TestServer</title>");
        response.getWriter().println("</head>");
        response.getWriter().println("<body>");
        response.getWriter().println("<form action=\"/reg\" method=\"POST\">");
        response.getWriter().println("Email: <input type=\"text\" name=\"email\"/>");
        response.getWriter().println("Password: <input type=\"password\" name=\"password\"/>");
        response.getWriter().println("<p></p>");
        response.getWriter().println("firstName: <input type=\"text\" name=\"firstName\"/>");
        response.getWriter().println("lastName: <input type=\"text\" name=\"lastName\"/>");
        response.getWriter().println("<input type=\"submit\" value=\"Регистрация\">");
        response.getWriter().println("</form>");
        response.getWriter().println("<a href=\"/\"> Назад </a>");
        response.getWriter().println("<hr>");
        response.getWriter().println("</body>");
        response.getWriter().println("</html>");
        response.setStatus(HttpServletResponse.SC_OK);

    }


    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        email = email.toLowerCase();

        if (userDAO.getUserByEmail(email)!=null) {
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

//        if ((!email.contains("@")) || (!email.contains("."))) {
//            response.setContentType("text/html;charset=utf-8");
//            response.getWriter().println("Неверный email");
//            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            return;
//        }

        userDAO.save(new User(email, password, firstName, lastName));
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println("Вы зарегистрированы");
        response.getWriter().println(" <br />");
        response.getWriter().println("<a href=\"/api/v1/dashboard\"> Назад </a>");
        response.setStatus(HttpServletResponse.SC_OK);



        }






    }


