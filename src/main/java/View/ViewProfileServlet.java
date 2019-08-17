package View;

import DAO.UserDAO;
import Main.CheckAuthUtil;
import Models.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static Main.GsonBuilderUtil.getGsonBuilder;
import static Main.GsonBuilderUtil.getGsonBuilderExpose;

public class ViewProfileServlet extends HttpServlet {

    UserDAO userDAO;

    public ViewProfileServlet(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        if (CheckAuthUtil.checkAuthUtil(userDAO,request,response)) return;

        User user = userDAO.getUserBySession(request.getSession().getId());

        String json = getGsonBuilder().toJson(user);

        response.setContentType("application/json");
        response.getWriter().println(json);
        response.setStatus(HttpServletResponse.SC_OK);

    }



}
