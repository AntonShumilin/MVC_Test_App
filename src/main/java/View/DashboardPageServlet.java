package View;

import DAO.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DashboardPageServlet extends HttpServlet {

    UserDAO userDAO;

    public DashboardPageServlet(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {


        userDAO.checkAuthUtil(userDAO,request,response);

        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println("<!DOCTYPE html>");
        response.getWriter().println("<head>");
        response.getWriter().println("<meta charset=\"UTF-8\"/>");
        response.getWriter().println("<title>TestServer</title>");
        response.getWriter().println("</head>");
        response.getWriter().println("<body>");
        response.getWriter().println("<p>Авторизация</p>");
        response.getWriter().println("<form action=\"/login\" method=\"POST\">");
        response.getWriter().println("Login: <input type=\"text\" name=\"email\"/>");
        response.getWriter().println("Password: <input type=\"password\" name=\"password\"/>");
        response.getWriter().println("<input type=\"submit\" value=\"Sign in\">");
        response.getWriter().println("</form>");
        response.getWriter().println("<br />");
        response.getWriter().println("<a href=\"/reg\"> Register </a>");
        response.getWriter().println(" <br />");
        response.getWriter().println(" <br />");
        response.getWriter().println(" <br />");
        response.getWriter().println("<a href=\"/api/v1/dashboard/me\"> /api/v1/dashboard/me </a>");
        response.getWriter().println(" <br />");
        response.getWriter().println("<a href=\"/api/v1/dashboard/checks\"> /api/v1/dashboard/checks </a>");
        response.getWriter().println(" <br />");
        response.getWriter().println("<a href=\"/api/v1/dashboard/checks/1\"> /api/v1/dashboard/checks/1 </a>");
        response.getWriter().println("<br />");
        response.getWriter().println("<a href=\"/api/v1/dashboard/products\"> /api/v1/dashboard/products </a>");
        response.getWriter().println("<br />");
        response.getWriter().println("<a href=\"/api/v1/dashboard/products/1\"> /api/v1/dashboard/products/1 </a>");
        response.getWriter().println("<br />");
        response.getWriter().println("<br />");
        response.getWriter().println("<fieldset>");
        response.getWriter().println("<legend>Loadcheck</legend>");
        response.getWriter().println("<form  action=\"/api/v1/dashboard/checks\" method=\"post\" enctype=\"multipart/form-data\">");
        response.getWriter().println("<input name=\"data\" type=\"file\"><br>");
        response.getWriter().println("<input type=\"submit\"><br>");
        response.getWriter().println("</form>");
        response.getWriter().println("</fieldset>");
        response.getWriter().println("<br />");
        response.getWriter().println("<a href=\"/api/v1/admin/users\"> /api/v1/admin/users </a>");
        response.getWriter().println("<br />");
        response.getWriter().println("<a href=\"/api/v1/admin/users/1\"> /api/v1/admin/users/1 </a>");
        response.getWriter().println("<br />");
        response.getWriter().println("<a href=\"/getConfigJson\"> /getConfigJson </a>");
        response.getWriter().println("<br />");
        response.getWriter().println("<a href=\"/api/v1/admin/oauth\"> /api/v1/admin/oauth </a>");
        response.getWriter().println("<br />");
        response.getWriter().println("<br />");
        response.getWriter().println("<fieldset>");
        response.getWriter().println("<legend>LoginJson</legend>");
        response.getWriter().println("<form  action=\"/api/v1/site/login\" method=\"post\" enctype=\"multipart/form-data\">");
        response.getWriter().println("<input name=\"data\" type=\"file\"><br>");
        response.getWriter().println("<input type=\"submit\"><br>");
        response.getWriter().println("</form>");
        response.getWriter().println("</fieldset>");
        response.getWriter().println("<fieldset>");
        response.getWriter().println("<br />");
        response.getWriter().println("<legend>RegisterJson</legend>");
        response.getWriter().println("<form  action=\"/api/v1/site/register\" method=\"post\" enctype=\"multipart/form-data\">");
        response.getWriter().println("<input name=\"data\" type=\"file\"><br>");
        response.getWriter().println("<input type=\"submit\"><br>");
        response.getWriter().println("</form>");
        response.getWriter().println("</fieldset>");
        response.getWriter().println("<br />");
        response.getWriter().println("<a href=\"/logout\"> Logout </a>");
        response.getWriter().println("</body>");
        response.getWriter().println("</html>");
        response.setStatus(HttpServletResponse.SC_OK);

    }
}