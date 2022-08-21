package vlue.tech.billsystem.vo;

import java.io.Serializable;

public class UserVO implements Serializable {

    private Integer id;
    private String username;
    private String key;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}
