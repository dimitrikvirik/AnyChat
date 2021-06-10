package git.dimitrikvirik.anychat.controller;

import git.dimitrikvirik.anychat.Exception.RecordException;
import git.dimitrikvirik.anychat.facade.UserFacade;
import git.dimitrikvirik.anychat.model.dto.UserAuthDTO;
import git.dimitrikvirik.anychat.model.dto.UserDTO;
import git.dimitrikvirik.anychat.model.entity.User;
import git.dimitrikvirik.anychat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthRestController {

    @Autowired
    UserFacade userFacade;
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> authentication(@RequestBody UserAuthDTO request){
         return userService.authentication(request);
    }
    @PostMapping("/reg")
    public UserDTO registration(@RequestBody User user) throws RecordException {
        return userFacade.registration(user);
    }
    @GetMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response){
        var securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }
}
