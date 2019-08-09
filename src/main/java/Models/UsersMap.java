package Models;

import java.util.HashMap;
import java.util.Map;

public class UsersMap {
      private final Map<String, User> sessionsMap;


    public UsersMap() {

        sessionsMap = new HashMap<>();

    }

    public void addSession (String session, User user) {sessionsMap.put(session, user);}

    public User getUserBySession (String session) {return sessionsMap.get(session);}



}
