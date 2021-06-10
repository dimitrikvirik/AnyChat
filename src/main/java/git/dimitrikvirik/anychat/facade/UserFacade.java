package git.dimitrikvirik.anychat.facade;

import git.dimitrikvirik.anychat.Exception.RecordException;
import git.dimitrikvirik.anychat.Exception.RecordNotFoundException;
import git.dimitrikvirik.anychat.config.SecurityConfig;
import git.dimitrikvirik.anychat.model.dto.UserDTO;
import git.dimitrikvirik.anychat.model.entity.User;
import git.dimitrikvirik.anychat.model.mapper.UserMapper;
import git.dimitrikvirik.anychat.model.param.UserParam;
import git.dimitrikvirik.anychat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserFacade {

    @Autowired
    UserService userService;
    @Transactional(rollbackOn = RecordException.class)
    public UserDTO registration(User user) throws RecordException {
       return UserMapper.toUserDTO(userService.registration(user));
    }

    public UserDTO getByUsername(String username) throws RecordNotFoundException {
        return UserMapper.toUserDTO(userService.getByUsername(username));
    }

    public UserDTO editByUsername(UserParam userParam, String username) throws RecordException {
        if(username.equals(userParam.getUsername())) {
            User editUser = UserMapper.toUser(userParam);

            userService.editByUsername(editUser, username);
            userParam.setPassword("");
            return userParam;
        }else {
            throw new RecordException("Current username not equal to request username!");
        }
    }


}
