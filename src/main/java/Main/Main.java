package Main;

import Config.Config;
import Config.ConfigFromFile;
import Controller.AuthServlet;
import Controller.RegServlet;
import Models.User;
import Models.UsersMap;
import View.GetJSONServlet;
import View.ViewProfileServlet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;


import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    public static Config config = Config.getInstance();
    public static ConfigFromFile configFromFile;
    public static void main(String[] args) throws Exception {
        //дефолтный конфиг


        // подготовка json builder
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();

        // читаем конфиг
        StringBuilder sb = new StringBuilder();
        String s = "";
        try (BufferedReader br = new BufferedReader(new FileReader("config.json"))) {

            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
            configFromFile = gson.fromJson(sb.toString(), ConfigFromFile.class);
            config.readConfig(configFromFile);
        } catch (Exception e){
            System.out.println("No config file / Config file not valid");
        }



        // создаем дефолтных юзеров
        UsersMap usersMap = new UsersMap();
        usersMap.addNewUser(new User("admin"));
        usersMap.addNewUser(new User("test"));
        usersMap.addNewUser(new User("1"));

        // сервлеты для обработки форм
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new RegServlet(usersMap)), "/reg/signUpForm");
        context.addServlet(new ServletHolder(new AuthServlet(usersMap)), "/auth");
        context.addServlet(new ServletHolder(new ViewProfileServlet(usersMap)), "/profile/viewprofile");
        context.addServlet(new ServletHolder(new GetJSONServlet(usersMap)), "/profile/getjson");



        // стартуем веб сервер
        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("html");
        //resource_handler.setResourceBase("/");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});

        Server server = new Server (config.app.port);
        server.setHandler(handlers);

        ServerConnector connector = new ServerConnector(server);
        connector.setHost(config.app.url);
        //connector.setPort(config.app.port);
        connector.setIdleTimeout(300000);
        server.addConnector(connector);

        server.start();
        server.join();
    }
}

