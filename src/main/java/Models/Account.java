package Models;

public class Account {

    String accountId;
    User user;

    public Account(String accountId, User user) {
        this.accountId = accountId;
        this.user = user;
    }
}
