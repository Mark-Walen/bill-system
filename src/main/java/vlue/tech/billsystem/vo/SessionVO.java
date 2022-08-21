package vlue.tech.billsystem.vo;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Validated
public class SessionVO implements Serializable {

    private Integer id;

    @NotBlank
    private String session;

    @NotBlank
    private String key;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "SessionVO{" +
                "id=" + id +
                ", session='" + session + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}
