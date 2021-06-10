package git.dimitrikvirik.anychat.controller;

import git.dimitrikvirik.anychat.Exception.RecordException;
import git.dimitrikvirik.anychat.Exception.RecordNotFoundException;
import git.dimitrikvirik.anychat.facade.UserFacade;
import git.dimitrikvirik.anychat.model.dto.UserDTO;
import git.dimitrikvirik.anychat.model.entity.User;
import git.dimitrikvirik.anychat.model.param.UserParam;
import git.dimitrikvirik.anychat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.List;

@RequestMapping("/api/v1/user")
@RestController
public class UserRestController {

    @Autowired
    UserFacade userFacade;
    @Autowired
    UserService userService;

    //USER
    @GetMapping
    @PreAuthorize("hasAnyAuthority('user:read')")
    @ResponseBody
    public UserDTO get(Authentication authentication) throws RecordNotFoundException {
      return   userFacade.getByUsername( authentication.getName());
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('user:edit')")
    public UserDTO edit(@RequestBody UserParam userParam, Authentication authentication) throws RecordException {
        return userFacade.editByUsername(userParam, authentication.getName());
    }

    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('user:delete')")
    public void delete(Authentication authentication, HttpServletResponse response) throws RecordNotFoundException, IOException {
        userService.setUserStatusByUsername(authentication.getName(), "DELETED");
        response.sendRedirect("/api/v1/auth/logout");
    }
    //ADMIN
    @GetMapping("/{username}")
    @PreAuthorize("hasAnyAuthority('users:read')")
    public User getByUsername(@PathVariable String username) throws RecordNotFoundException {
        return userService.getByUsername(username);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('users:read')")
    public List<User> getAll(){
     return   userService.getAll();
    }

    @PostMapping("/role/{username}")
    @PreAuthorize("hasAnyAuthority('users:setRole')")
    public void setUserRole(@PathVariable String username, @RequestBody String role) throws RecordNotFoundException {
        userService.setUserRoleByUsername(username, role);
    }

    @PostMapping("/status/{username}")
    @PreAuthorize("hasAnyAuthority('users:setStatus')")
    public void setUserStatus(@PathVariable String username, @RequestBody String status) throws RecordNotFoundException {
        userService.setUserRoleByUsername(username, status);
    }

    @PutMapping("/{username}")
    @PreAuthorize("hasAnyAuthority('users:edit')")
    public User editByUsername(@PathVariable String username, @RequestBody User user) throws RecordNotFoundException {
         userService.editByUsername(user, username);
         return user;
    }

    @DeleteMapping("/{username}")
    @PreAuthorize("hasAnyAuthority('users:delete')")
    public void deleteByUsername(@PathVariable String username) throws RecordNotFoundException {
        userService.setUserStatusByUsername(username, "DELETED");
    }

    @DeleteMapping("/{username}/force")
    @PreAuthorize("hasAnyAuthority('users:forceDelete')")
    public void forceDeleteByUsername(@PathVariable String username) throws RecordNotFoundException {
        userService.forceDeleteByUsername(username);
    }


}
