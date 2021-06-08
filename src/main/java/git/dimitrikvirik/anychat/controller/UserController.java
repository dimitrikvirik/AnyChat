package git.dimitrikvirik.anychat.controller;

import git.dimitrikvirik.anychat.Exception.RecordAlreadyExistException;
import git.dimitrikvirik.anychat.Exception.RecordException;
import git.dimitrikvirik.anychat.facade.UserFacade;
import git.dimitrikvirik.anychat.model.dto.UserDTO;
import git.dimitrikvirik.anychat.model.entity.User;
import git.dimitrikvirik.anychat.model.param.UserParam;
import git.dimitrikvirik.anychat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    UserFacade userFacade;

    @PostMapping("/reg")
    public UserDTO registration(@RequestBody UserParam user) throws RecordException {
      return   userFacade.create(user);
    }
}
