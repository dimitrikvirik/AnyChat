package git.dimitrikvirik.anychat.facade;

import git.dimitrikvirik.anychat.Exception.RecordAlreadyExistException;
import git.dimitrikvirik.anychat.Exception.RecordCreateException;
import git.dimitrikvirik.anychat.Exception.RecordException;
import git.dimitrikvirik.anychat.Exception.RecordNotFoundException;
import git.dimitrikvirik.anychat.config.SecurityConfig;
import git.dimitrikvirik.anychat.model.dto.UserDTO;
import git.dimitrikvirik.anychat.model.entity.User;
import git.dimitrikvirik.anychat.model.mapper.UserMapper;
import git.dimitrikvirik.anychat.model.param.UserParam;
import git.dimitrikvirik.anychat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class UserFacade {

    @Autowired
    UserService userService;
    @Transactional(rollbackOn = RecordException.class)
    public UserDTO create(UserParam user) throws RecordException {
        User newUser =  userService.create(user.toUser());;
        if(newUser != null){
            return UserMapper.toUserDTO(newUser);
        }else
            return null;
    }

    public List<UserDTO> getAll(){
       return UserMapper.toUserDTOList(userService.getAll());
    }


    public UserDTO getById(int id) throws RecordNotFoundException {
        return UserMapper.toUserDTO(userService.getById(id));
    }

    public void deleteById(int id) throws RecordNotFoundException {
        userService.deleteById(id);
    }
}
