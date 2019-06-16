package restaurant.voting.service;

import restaurant.voting.model.User;
import restaurant.voting.to.UserTo;
import restaurant.voting.util.exceptions.NotFoundException;

import java.util.List;

public interface UserService {

    User create(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    void update(User user);

    void update(UserTo user);

    List<User> getAll();

}