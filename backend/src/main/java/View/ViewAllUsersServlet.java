package View;

import DAO.UserDAO;
import Models.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static View.UtilMethods.*;


public class ViewAllUsersServlet extends HttpServlet {

    UserDAO userDAO;

    public ViewAllUsersServlet(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

       if (userDAO.checkAdminAuthUtil(userDAO,request,response)) return;

        List<User> usersList = userDAO.findAllUsers();

        sortListOfJson(usersList, request.getParameter("sort"));
        sendListOfJson(usersList, request, response);

    }

    }
