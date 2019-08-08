package View;

import DAO.UserDAO;
import Main.Main;
import Models.User;
import Models.UsersMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetJSONServlet extends HttpServlet {
    UserDAO userDAO;

    public GetJSONServlet(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        String json = gson.toJson(Main.config);

        response.setContentType("application/json");
        response.getWriter().println(json);
        response.setStatus(HttpServletResponse.SC_OK);

    }



}
