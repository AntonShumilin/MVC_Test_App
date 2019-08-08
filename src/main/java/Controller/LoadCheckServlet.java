package Controller;

import DAO.UserDAO;
import Models.CheckWeb;
import Models.UsersMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import java.util.Iterator;
import java.util.List;

public class LoadCheckServlet extends HttpServlet {

    UserDAO userDAO;

    public LoadCheckServlet(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        StringBuilder sb = new StringBuilder();
        String s = "";
        try {
            List items = upload.parseRequest(request);
            Iterator iterator = items.iterator();
            while (iterator.hasNext()) {
                FileItem item = (FileItem) iterator.next();
                BufferedReader br = new BufferedReader(new InputStreamReader(item.getInputStream()));

                if (!item.isFormField()) {

                    while ((s = br.readLine()) != null) {
                        sb.append(s);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("херня");
        }
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        CheckWeb checkWeb = gson.fromJson(sb.toString(), CheckWeb.class);
        userDAO.getUserBySession(request.getSession().getId()).addCheck(checkWeb);


        String json = gson.toJson(checkWeb);
        response.setContentType("application/json");
        response.getWriter().println(json);
        response.setStatus(HttpServletResponse.SC_OK);
    }




    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();

        CheckWeb checkWeb;

        StringBuilder sb = new StringBuilder();
        String s = "";
        try (BufferedReader br = new BufferedReader(new FileReader("check.json"))) {

            while ((s = br.readLine()) != null) {
                sb.append(s);
            }



        } catch (Exception e){
            System.out.println("No json file / json file not valid");
        }
        checkWeb = gson.fromJson(sb.toString(), CheckWeb.class);

        userDAO.getUserBySession(request.getSession().getId()).addCheck(checkWeb);


        String json = gson.toJson(checkWeb);
        response.setContentType("application/json");
        response.getWriter().println(json);
        response.setStatus(HttpServletResponse.SC_OK);


    }

}
