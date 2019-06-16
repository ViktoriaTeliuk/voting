package restaurant.voting.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import restaurant.voting.model.User;
import restaurant.voting.service.UserService;
import restaurant.voting.to.UserTo;
import restaurant.voting.util.exceptions.IllegalRequestDataException;

import java.util.List;

import static restaurant.voting.util.UserUtil.prepareToSave;
import static restaurant.voting.util.UserUtil.updateFromTo;

public abstract class AbstractUserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService service;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UniqueMailValidator emailValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(emailValidator);
    }

    public List<User> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public User get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public User create(User user) {
        log.info("create {}", user);
        if (!user.isNew()) {
            throw new IllegalRequestDataException(user + " must be new (id=null)");
        }
        return service.create(user);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public void update(User user, int id) {
        log.info("update {} with id={}", user, id);
        if (user.getId() != id) {
            throw new IllegalRequestDataException(user + " must be with id=" + id);
        }
        service.update(user);
    }

    public void update(UserTo userTo, int id) {
        log.info("update {} with id={}", userTo, id);
        if (userTo.getId() != id) {
            throw new IllegalRequestDataException(userTo + " must be with id=" + id);
        }
        service.update(prepareToSave(updateFromTo(get(userTo.getId()), userTo), passwordEncoder));
    }

    public User getByMail(String email) {
        log.info("getByEmail {}", email);
        return service.getByEmail(email);
    }

}