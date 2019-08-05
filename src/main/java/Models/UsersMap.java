package Models;

import java.util.HashMap;
import java.util.Map;

public class UsersMap {
    private final Map<String, User> loginsMap;
    private final Map<String, User> sessionsMap;


    public UsersMap() {
        loginsMap = new HashMap<>();
        sessionsMap = new HashMap<>();

    }

    public void addNewUser(User user) {
        loginsMap.put(user.getLogin(), user);
    }

    public User getUserByLogin(String login) {
        return loginsMap.get(login);
    }

    public void addSession (String session, User user) {sessionsMap.put(session, user);}

    public User getUserBySession (String session) {return sessionsMap.get(session);}



}
