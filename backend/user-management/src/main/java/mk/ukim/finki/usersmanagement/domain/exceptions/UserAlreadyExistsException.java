package mk.ukim.finki.usersmanagement.domain.exceptions;

public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException(String email){
        super(String.format("User with email: %s already exists", email));
    }
}
