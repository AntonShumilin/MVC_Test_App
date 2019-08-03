package View;

import Models.User;
import Models.UsersMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ViewProfileServlet extends HttpServlet {

    private UsersMap usersMap;

    public ViewProfileServlet(UsersMap usersMap) {
        this.usersMap = usersMap;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        User user = usersMap.getUserBySession(request.getSession().getId());

        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println("login " + user.getLogin() + "<br />");
        response.getWriter().println("password " + user.getPassword() + "<br />");
        response.getWriter().println("FirsName " + user.getFirstName() + "<br />");
        response.getWriter().println("LastName " + user.getLastName() + "<br />");
        response.getWriter().println("CreatedAt " + user.getCreatedAt() + "<br />");
        response.getWriter().println("UpdatedAt " + user.getUpdatedAt() + "<br />");
        response.getWriter().println("DeletedAt " + user.getDeletedAt() + "<br />");
        response.setStatus(HttpServletResponse.SC_OK);

    }



}
