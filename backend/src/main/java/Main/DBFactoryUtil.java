package Main;

import Models.Item;
import Models.Receipt;
import Models.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.concurrent.TimeUnit;


public class DBFactoryUtil {
    private static final String hibernate_show_sql = "true";
    private static final String hibernate_hbm2ddl_auto = "create";

    public final SessionFactory sessionFactory;

    public DBFactoryUtil() throws InterruptedException{
        Configuration configuration = getPostgresConfiguration();
        sessionFactory = createSessionFactory(configuration);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private Configuration getPostgresConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Receipt.class);
        configuration.addAnnotatedClass(Item.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        configuration.setProperty("hibernate.connection.url", Main.config.database);
        configuration.setProperty("hibernate.connection.username", "postgres");
        configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
        configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
        configuration.setProperty("hibernate.format_sql", "true");
        return configuration;
    }

//    public void printConnectInfo() {
//        try {
//            SessionFactoryImpl sessionFactoryImpl = (SessionFactoryImpl) sessionFactory;
//            Connection connection = sessionFactoryImpl.getConnectionProvider().getConnection();
//            System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
//            System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
//            System.out.println("Driver: " + connection.getMetaData().getDriverName());
//            System.out.println("Autocommit: " + connection.getAutoCommit());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    private static SessionFactory createSessionFactory(Configuration configuration) throws InterruptedException{

        SessionFactory sf = null;
        do {
            try {
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
                builder.applySettings(configuration.getProperties());
                ServiceRegistry serviceRegistry = builder.build();
                sf = configuration.buildSessionFactory(serviceRegistry);
                return sf;
            } catch (Exception e) {
                System.out.println("No DB connection, next try in 5 sec...");;
                TimeUnit.SECONDS.sleep(5);
            }
        } while (sf==null);
    return sf;
    }

    public void sessionFactoryClose() {
        sessionFactory.close();
    }


}
