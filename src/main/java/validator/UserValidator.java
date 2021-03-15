package validator;

import entity.User;

public class UserValidator {

    public static void validateCreateNewUserFlow(User user) {
        if (user == null || user.getName() == null)
            throw new IllegalArgumentException("User is null or name is empty/null.");
        if (user.getName().length() < 6)
            throw new IllegalArgumentException("Name must be at least 6 characters long.");
        if(user.getPassword().length() < 6)
            throw new IllegalArgumentException("Password must be at least 6 characters long.");
        if(user.getUsername().length() < 6)
            throw new IllegalArgumentException("Username must be at least 6 characters long.");
    }
}
