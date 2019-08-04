package Config;


import java.util.Arrays;
import java.util.List;

public class Config {
    public App app = new App();
    public String database ="jdbc:postgresql://localhost:5432/test";
    public Oauth oauth = new Oauth();
    public Sparkpost sparkpost = new Sparkpost();
    public Google google = new Google();
    public Uploadcare uploadcare = new Uploadcare();
    public Maildev maildev = new Maildev();
    public Github github = new Github();
    public Telegram telegram = new Telegram();

    public void readConfig (ConfigFromFile cff) {
        if (cff.app.env != null) app.env = cff.app.env;
        if (cff.app.secret != null) app.secret = cff.app.secret;
        if (cff.app.url != null) app.url = cff.app.url;
        if (cff.app.port != 0) app.port = cff.app.port;

        if (cff.database != null) database = cff.database;

        if (cff.oauth.clientId != null) oauth.clientId = cff.oauth.clientId;
        if (cff.oauth.secret != null) oauth.secret = cff.oauth.secret;
        if (cff.oauth.adminUsers != null) oauth.adminUsers = cff.oauth.adminUsers;

        if (cff.sparkpost.apiKey != null) sparkpost.apiKey = cff.sparkpost.apiKey;
        if (cff.sparkpost.ipPool != null) sparkpost.ipPool = cff.sparkpost.ipPool;
        if (cff.sparkpost.returnPath != null) sparkpost.returnPath = cff.sparkpost.returnPath;

        if (cff.google.analytics != null) google.analytics = cff.google.analytics;
        if (cff.google.maps != null) google.maps = cff.google.maps;

        if (cff.maildev.host != null) maildev.host = cff.maildev.host;
        if (cff.maildev.web != 0) maildev.web = cff.maildev.web;
        if (cff.maildev.smtp != 0) maildev.smtp = cff.maildev.smtp;
        if (cff.maildev.basePathname != null) maildev.basePathname = cff.maildev.basePathname;

        if (cff.github.accessToken != null) github.accessToken = cff.github.accessToken;

        if (cff.telegram.bot != null) telegram.bot = cff.telegram.bot;
        if (cff.telegram.leadsChat != null) telegram.leadsChat = cff.telegram.leadsChat;
    }

    //Singleton
    public static Config instance;
    public Config() {};
    public static Config getInstance(){
        if(instance == null){
            instance = new Config();
        }
        return instance;
    }
    public class App {
        public String env = "development";
        public String secret = "dev-secret";
        public String url = "localhost";
        public Integer port = 8080;
    }
    class Oauth {
        String clientId = "apps.googleusercontent.com";
        String secret = "1111111";
        List<String> adminUsers = Arrays.asList("thundergod@gmail.com", "sedruid@gmail.com");
    }
    class Sparkpost {
        String apiKey = "qqqqq";
        String ipPool = "";
        String returnPath = "bounces@bounces.example.com";
    }
    class Google {
        String analytics = "UA-kkkkkkkkkk";
        String maps = "a;ksmdcsakd";
    }
    class Uploadcare {
        String publicKey = "a;lksdclkdsac";
    }
    class Maildev {
        String host = "127.0.0.1";
        Integer web = 3201;
        Integer smtp = 3202;
        String basePathname = "/mailbox";
    }
    class Github {
        String accessToken = "a;ksmfd;ksadmv";
    }

    class  Telegram {
        String bot = "a;lskjdc;ksamcwacdm";
        String leadsChat = ";asmcsamdc";
    }
}
