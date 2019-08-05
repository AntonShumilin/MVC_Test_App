package Controller;

import Config.ConfigFromFile;
import Main.Main;
import Models.Check;
import Models.UsersMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import static Main.Main.configFromFile;

public class LoadCheckServlet extends HttpServlet {

    private UsersMap usersMap;

    public LoadCheckServlet(UsersMap usersMap) {
        this.usersMap = usersMap;
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
