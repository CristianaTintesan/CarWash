package mappers;

import dto.UserDto;
import entity.User;

public class UserMapper {

    public static UserDto entityToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        return userDto;
    }

    public static User fromDto(UserDto userDto) {
        return null;
    }

}
