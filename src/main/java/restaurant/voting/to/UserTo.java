package restaurant.voting.to;

import org.hibernate.validator.constraints.SafeHtml;
import restaurant.voting.HasEmail;
import restaurant.voting.util.UserValidation;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class UserTo extends BaseTo implements HasEmail, Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(groups = UserValidation.class)
    @Size(min = 2, max = 100, groups = UserValidation.class)
    @SafeHtml
    private String name;

    @Email(groups = UserValidation.class)
    @NotBlank(groups = UserValidation.class)
    @Size(max = 100, groups = UserValidation.class)
    @SafeHtml // https://stackoverflow.com/questions/17480809
    private String email;

    @Size(min = 5, max = 32, groups = UserValidation.class)
    private String password;

    public UserTo() {
    }

    public UserTo(Integer id, String name, String email, String password) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
