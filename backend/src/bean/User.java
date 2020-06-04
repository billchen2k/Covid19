package bean;

//用户信息类
public class User {
    private int uid = -1;
    private String username = "N/A";
    private String password = "N/A";
    private String email = "N/A";
    private String registration_date = "N/A";
    private String role = "user";
    private String password_last_changed = "N/A";

    public User(){}
    public User(int uid, String username, String password, String email, String registration_date, String role, String password_last_changed) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.email = email;
        this.registration_date = registration_date;
        this.role = role;
        this.password_last_changed = password_last_changed;
    }

    public int getUid() {
        return uid;
    }
    public void setUid(int uid) { this.uid = uid; }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegistration_time() {
        return registration_date;
    }
    public void setRegistration_time(String registration_date) {
        this.registration_date = registration_date;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword_last_changed() {
        return password_last_changed;
    }
    public void setPassword_last_changed(String password_last_changed) { this.password_last_changed = password_last_changed; }
}
