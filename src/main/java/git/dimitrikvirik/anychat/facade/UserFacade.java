package git.dimitrikvirik.anychat.facade;

import git.dimitrikvirik.anychat.Exception.RecordAlreadyExistException;
import git.dimitrikvirik.anychat.Exception.RecordCreateException;
import git.dimitrikvirik.anychat.Exception.RecordException;
import git.dimitrikvirik.anychat.model.dto.UserDTO;
import git.dimitrikvirik.anychat.model.mapper.UserMapper;
import git.dimitrikvirik.anychat.model.param.UserParam;
import git.dimitrikvirik.anychat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserFacade {

    @Autowired
    UserService userService;

    public UserDTO create(UserParam user) throws RecordException {
       return UserMapper.toUserDTO(
               userService.create( user.toUser()));
    }

}
