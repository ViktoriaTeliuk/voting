package restaurant.voting.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import restaurant.voting.HasEmail;
import restaurant.voting.model.User;
import restaurant.voting.repository.UserRepository;
import restaurant.voting.util.exceptions.DuplicateEmailException;

@Component
public class UniqueMailValidator implements org.springframework.validation.Validator {

    @Autowired
    private UserRepository repository;

    @Override
    public boolean supports(Class<?> clazz) {
        return HasEmail.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        HasEmail user = (HasEmail) target;
        User dbUser = repository.getByEmail(user.getEmail().toLowerCase());
        if (dbUser != null && !dbUser.getId().equals(user.getId())) {
            throw new DuplicateEmailException(dbUser.getEmail());
        }
    }
}
