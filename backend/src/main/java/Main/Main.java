package Main;

import Config.Config;
import Config.ConfigFromFile;
import Controller.*;
import DAO.ItemDAO;
import DAO.ReceiptDAO;
import DAO.UserDAO;
import View.*;
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

import static View.UtilMethods.*;


public class Main {
    public static Config config = Config.getInstance();
    public static ConfigFromFile configFromFile;


    public static void main(String[] args) throws Exception {

        // читаем конфиг
        StringBuilder sb = new StringBuilder();
        String s = "";
        try (BufferedReader br = new BufferedReader(new FileReader("config.json"))) {

            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
            configFromFile = getGsonBuilder().fromJson(sb.toString(), ConfigFromFile.class);
            config.readConfig(configFromFile);
        } catch (Exception e){
            System.out.println("No config file / Config file not valid");
        }

        DBFactoryUtil dbFactoryUtil = new DBFactoryUtil();
        UserDAO userDAO = new UserDAO(dbFactoryUtil);
        ReceiptDAO receiptDAO = new ReceiptDAO(dbFactoryUtil);
        ItemDAO itemDAO = new ItemDAO(dbFactoryUtil);



        // сервлеты для обработки форм
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new DashboardPageServlet(userDAO)), "/api/v1/dashboard");
        context.addServlet(new ServletHolder(new AuthServlet(userDAO)), "/login");
        context.addServlet(new ServletHolder(new RegServlet(userDAO)), "/reg");
        context.addServlet(new ServletHolder(new LogoutServlet(userDAO)), "/logout");
        context.addServlet(new ServletHolder(new ViewAndLoadCheks(userDAO, receiptDAO)), "/api/v1/dashboard/checks");
        context.addServlet(new ServletHolder(new ViewProfileServlet(userDAO)), "/api/v1/dashboard/me");
        context.addServlet(new ServletHolder(new GetJSONServlet(userDAO)), "/getConfigJson");
        context.addServlet(new ServletHolder(new ViewAllProductsByUserServlet(userDAO, receiptDAO)), "/api/v1/dashboard/products");
        context.addServlet(new ServletHolder(new ViewAllUsersServlet(userDAO)), "/api/v1/admin/users");
        context.addServlet(new ServletHolder(new AdminAuthServlet(userDAO)), "/api/v1/site/login");
        context.addServlet(new ServletHolder(new AdminRegServlet(userDAO)), "/api/v1/site/register");
        context.addServlet(new ServletHolder(new OauthServlet(userDAO)), "/api/v1/admin/oauth");

        ServletHolder holderViewCheckById = new ServletHolder("ViewCheckById", new ViewCheckByIdServlet(userDAO, receiptDAO));
        context.addServlet(holderViewCheckById, "/api/v1/dashboard/checks/*");

        ServletHolder holderViewProductById = new ServletHolder("ViewProductById", new ViewProductByIdServlet(itemDAO, userDAO));
        context.addServlet(holderViewProductById, "/api/v1/dashboard/products/*");

        ServletHolder holderViewUserById = new ServletHolder("ViewUserById", new ViewUserById(userDAO));
        context.addServlet(holderViewUserById, "/api/v1/admin/users/*");



        // стартуем веб сервер
        ResourceHandler resource_handler = new ResourceHandler();
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

