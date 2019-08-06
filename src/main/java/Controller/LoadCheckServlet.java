package Controller;

import Config.ConfigFromFile;
import Main.Main;
import Models.Check;
import Models.UsersMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static Main.Main.configFromFile;

public class LoadCheckServlet extends HttpServlet {

    private UsersMap usersMap;

    public LoadCheckServlet(UsersMap usersMap) {
        this.usersMap = usersMap;
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
        Check check = gson.fromJson(sb.toString(), Check.class);
        usersMap.getUserBySession(request.getSession().getId()).addCheck(check);


        String json = gson.toJson(check);
        response.setContentType("application/json");
        response.getWriter().println(json);
        response.setStatus(HttpServletResponse.SC_OK);
    }




    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();

        Check check;

        StringBuilder sb = new StringBuilder();
        String s = "";
        try (BufferedReader br = new BufferedReader(new FileReader("check.json"))) {

            while ((s = br.readLine()) != null) {
                sb.append(s);
            }



        } catch (Exception e){
            System.out.println("No json file / json file not valid");
        }
        check = gson.fromJson(sb.toString(), Check.class);

        usersMap.getUserBySession(request.getSession().getId()).addCheck(check);


        String json = gson.toJson(check);
        response.setContentType("application/json");
        response.getWriter().println(json);
        response.setStatus(HttpServletResponse.SC_OK);


    }

}
