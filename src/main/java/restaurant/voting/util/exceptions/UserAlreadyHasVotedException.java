package restaurant.voting.util.exceptions;

public class UserAlreadyHasVotedException extends RuntimeException {
    public UserAlreadyHasVotedException(String msg) {
        super(msg);
    }
}
