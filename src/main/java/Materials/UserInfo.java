package Materials;

import java.util.HashMap;
import java.util.Map;

public class UserInfo {
    public static HashMap<String, UserInfo> usersData = new HashMap<>();
    private String login;
    private String password;
    private String mailAddress;

    public UserInfo(String login, String password, String mailAddress) {
        this.login = login;
        this.password = password;
        this.mailAddress = mailAddress;
    }

    public UserInfo(String login, String password) {
        this.login = login;
        this.password = password;
    }
    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

}
