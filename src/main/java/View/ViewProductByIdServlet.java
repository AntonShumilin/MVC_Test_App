package View;

import DAO.ItemDAO;
import DAO.UserDAO;
import Main.CheckAuthUtil;
import Models.Item;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static Main.GsonBuilderUtil.getGsonBuilderExpose;

public class ViewProductByIdServlet extends HttpServlet {

    ItemDAO itemDAO;
    UserDAO userDAO;

    public ViewProductByIdServlet(ItemDAO itemDAO, UserDAO userDAO) {
        this.itemDAO = itemDAO;
        this.userDAO = userDAO;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        if (CheckAuthUtil.checkAuthUtil(userDAO,request,response)) return;

        String pathInfo = request.getPathInfo();
        pathInfo = pathInfo.replace("/","");

        long itemId;
        try {
            itemId = Integer.parseInt(pathInfo);
        } catch (Exception e) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("косяк");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Item item = itemDAO.findItemById(itemId);

        if (item == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        response.setContentType("application/json");
        response.getWriter().println(getGsonBuilderExpose().toJson(item));
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
