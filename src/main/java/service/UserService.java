package service;

import dto.UserDto;
import entity.User;
import exception.CustomErrorMessages;
import exception.EntityNotExistException;
import mappers.UserMapper;
import repository.UserRepo;
import utils.ApplicationUtils;
import validator.UserValidator;

public class UserService {

    private UserRepo userRepo;

    public UserService() {
        this.userRepo = new UserRepo();
    }

    public UserDto getUser(String id) {
        if (id == null || id.equals("") || id.length() < 36) {
            throw new IllegalArgumentException(CustomErrorMessages.INVALID_ID_MESSAGE);
        }

        User user = userRepo.findUser(id);

        if (user == null) {
            throw new EntityNotExistException("No user having id " + id + "exists.");
        }

        return UserMapper.entityToDto(user);
    }

    public User addUser(User user) {
        UserValidator.validateCreateNewUserFlow(user);
        //insert in DB
        user.setId(ApplicationUtils.generateNewUUID());
        userRepo.insertNewUser(user);
        return null;
    }

    public User findUser(String username) {
        User user = userRepo.findUser(username);
        return user;
    }
}
