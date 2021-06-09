package git.dimitrikvirik.anychat.model.mapper;

import git.dimitrikvirik.anychat.model.dto.UserDTO;
import git.dimitrikvirik.anychat.model.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {
    public static UserDTO toUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setAge(user.getAge());
        userDTO.setUsername(user.getUsername());
        userDTO.setFirstname(user.getFirstname());
        userDTO.setLastname(user.getLastname());
        userDTO.setId(user.getId());
       return userDTO;
    }
    public static List<UserDTO> toUserDTOList(List<User> userList){
        List<UserDTO> userDTOList = new ArrayList<>();
        userList.forEach((user -> {userDTOList.add(UserMapper.toUserDTO(user));}));
        return userDTOList;
    }
    public static User toUser(UserDTO userDTO){
        User user = new User();
        user.setAge(userDTO.getAge());
        user.setUsername(userDTO.getUsername());
        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setId(userDTO.getId());
        return user;
    }
}
