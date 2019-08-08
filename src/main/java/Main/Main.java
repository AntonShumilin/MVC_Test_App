package Main;

import Config.Config;
import Config.ConfigFromFile;
import Controller.AuthServlet;
import Controller.LoadCheckServlet;
import Controller.RegServlet;
import DAO.UserDAO;
import View.GetJSONServlet;
import View.ViewAllCheks;
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
        //конфигурация и коннект к БД
        DBFactoryUtil dbFactoryUtil = new DBFactoryUtil();
        dbFactoryUtil.printConnectInfo();
        UserDAO userDAO = new UserDAO(dbFactoryUtil);


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

        // сервлеты для обработки форм
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new RegServlet(userDAO)), "/reg/signUpForm");
        context.addServlet(new ServletHolder(new AuthServlet(userDAO)), "/auth");
        context.addServlet(new ServletHolder(new ViewProfileServlet(userDAO)), "/viewprofile");
        context.addServlet(new ServletHolder(new GetJSONServlet(userDAO)), "/getConfigJson");
        context.addServlet(new ServletHolder(new LoadCheckServlet(userDAO)), "/loadcheck");
        context.addServlet(new ServletHolder(new ViewAllCheks(userDAO)), "/viewallchecks");



        // стартуем веб сервер
        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("html");
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});

        Server server = new Server (config.app.port);
        server.setHandler(handlers);

        ServerConnector connector = new ServerConnector(server);
        connector.setHost(config.app.url);
        connector.setIdleTimeout(300000);
        server.addConnector(connector);

        server.start();
        server.join();
    }
}

