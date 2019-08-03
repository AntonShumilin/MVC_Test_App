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
        public String url = "http://localhost:8080";
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
