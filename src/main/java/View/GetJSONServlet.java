package View;

import DAO.UserDAO;
import Main.Main;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import Main.CheckAuthUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static Main.GsonBuilderUtil.getGsonBuilder;

public class GetJSONServlet extends HttpServlet {
    UserDAO userDAO;

    public GetJSONServlet(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        if (CheckAuthUtil.checkAuthUtil(userDAO,request,response)) return;

        String json = getGsonBuilder().toJson(Main.config);

        response.setContentType("application/json");
        response.getWriter().println(json);
        response.setStatus(HttpServletResponse.SC_OK);

    }



}
