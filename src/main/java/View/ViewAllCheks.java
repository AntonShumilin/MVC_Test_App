package View;

import Models.Check;
import Models.User;
import Models.UsersMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class ViewAllCheks extends HttpServlet {

    private UsersMap usersMap;

    public ViewAllCheks(UsersMap usersMap) {
        this.usersMap = usersMap;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        User user = usersMap.getUserBySession(request.getSession().getId());
        ArrayList<String> checkView = new ArrayList<>();
        for (Map.Entry<String, Check> entry: user.userChecks.entrySet()) {
            String key = entry.getKey();
            checkView.add("Date " + user.getCheckByFS(key).dateTime + " " + "Sum nal " + user.getCheckByFS(key).cashTotalSum + " " + "Sum beznal " + user.getCheckByFS(key).ecashTotalSum);
        }
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(checkView);


    }



}
