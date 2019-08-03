package Config;


import java.util.Arrays;
import java.util.List;

public class ConfigFromFile {
    public App app = new App();
    public String database = null;
    public Oauth oauth = new Oauth();
    public Sparkpost sparkpost = new Sparkpost();
    public Google google = new Google();
    public Uploadcare uploadcare = new Uploadcare();
    public Maildev maildev = new Maildev();
    public Github github = new Github();
    public Telegram telegram = new Telegram();


    public class App {
        public String env = null;
        public String secret =  null;
        public String url =  null;
        public Integer port = 0;
    }

    class Oauth {
        String clientId = null;
        String secret = null;
        List<String> adminUsers =  null;
    }
    class Sparkpost {
        String apiKey = null;
        String ipPool = null;
        String returnPath =  null;
    }
    class Google {
        String analytics = null;
        String maps =  null;
    }
    class Uploadcare {
        String publicKey =  null;
    }
    class Maildev {
        String host =  null;
        Integer web = 0;
        Integer smtp = 0;
        String basePathname =  null;
    }
    class Github {
        String accessToken =  null;
    }

    class  Telegram {
        String bot =  null;
        String leadsChat =  null;
    }
}
