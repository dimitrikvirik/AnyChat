package git.dimitrikvirik.anychat.controller;

import git.dimitrikvirik.anychat.Exception.RecordException;
import git.dimitrikvirik.anychat.Exception.RecordNotFoundException;
import git.dimitrikvirik.anychat.facade.UserFacade;
import git.dimitrikvirik.anychat.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/user")
@RestController
public class UserRestController {

    @Autowired
    UserFacade userFacade;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('user:write')")
    public UserDTO registration(@RequestBody UserDTO user) throws RecordException {
      return   userFacade.create(user);
    }
    @GetMapping
    @PreAuthorize("hasAnyAuthority('user:read')")
    public List<UserDTO> getAll(){
        return userFacade.getAll();
    }

    @GetMapping("/{username}")
    @PreAuthorize("hasAnyAuthority('user:read') and hasRole('ADMIN')")
    public UserDTO getByUsername(@PathVariable String username) throws RecordNotFoundException {
        return userFacade.getByUsername(username);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('user:delete')")
    public void deleteById(@PathVariable int id) throws  RecordNotFoundException{
        userFacade.deleteById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('user:edit')")
    public UserDTO editById(@PathVariable int id, @RequestBody UserDTO userDTO) throws RecordNotFoundException {
       return userFacade.editById(id, userDTO);
    }
}
