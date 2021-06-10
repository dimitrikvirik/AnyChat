package git.dimitrikvirik.anychat.model.mapper;

import git.dimitrikvirik.anychat.model.dto.UserAuthDTO;
import git.dimitrikvirik.anychat.model.dto.UserDTO;
import git.dimitrikvirik.anychat.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {


    public static UserDTO toUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setAge(user.getAge());
        userDTO.setUsername(user.getUsername());
        userDTO.setFirstname(user.getFirstname());
        userDTO.setLastname(user.getLastname());
       return userDTO;
    }
    public static List<UserDTO> toUserDTO(List<User> userList){
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
        return user;
    }
    public static UserAuthDTO toUserAuthDTO(User user){
        UserAuthDTO userAuthDTO = new UserAuthDTO();
        userAuthDTO.setUsername(user.getUsername());
        userAuthDTO.setPassword(user.getPassword());
        return userAuthDTO;
    }


}
