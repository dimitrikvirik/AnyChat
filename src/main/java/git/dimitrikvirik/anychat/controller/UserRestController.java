package git.dimitrikvirik.anychat.controller;

import git.dimitrikvirik.anychat.Exception.RecordAlreadyExistException;
import git.dimitrikvirik.anychat.Exception.RecordException;
import git.dimitrikvirik.anychat.Exception.RecordNotFoundException;
import git.dimitrikvirik.anychat.facade.UserFacade;
import git.dimitrikvirik.anychat.model.dto.UserDTO;
import git.dimitrikvirik.anychat.model.entity.User;
import git.dimitrikvirik.anychat.model.param.UserParam;
import git.dimitrikvirik.anychat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/user")
@RestController
public class UserRestController {

    @Autowired
    UserFacade userFacade;




    @PostMapping("/reg")
    @PreAuthorize("hasAnyAuthority('user:write')")
    public UserDTO registration(@RequestBody UserParam user) throws RecordException {
      return   userFacade.create(user);
    }
    @GetMapping
    @PreAuthorize("hasAnyAuthority('user:read')")
    public List<UserDTO> getAll(){
        return userFacade.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('user:read')")
    public UserDTO getById(@PathVariable int id) throws RecordNotFoundException {
        return userFacade.getById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('user:delete')")
    public void deleteById(@PathVariable int id) throws  RecordNotFoundException{
        userFacade.deleteById(id);
    }
}
