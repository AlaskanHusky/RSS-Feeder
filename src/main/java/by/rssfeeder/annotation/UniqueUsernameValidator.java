package by.rssfeeder.annotation;

import by.rssfeeder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired
    private UserRepository userRepository;

    public void initialize(UniqueUsername uniqueUsername) {

    }

    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(userRepository == null) {
            return true;
        }
        return userRepository.findByName(s) == null;
    }
}
