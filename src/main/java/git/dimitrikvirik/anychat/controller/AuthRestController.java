package git.dimitrikvirik.anychat.controller;

import git.dimitrikvirik.anychat.Exception.RecordNotFoundException;
import git.dimitrikvirik.anychat.model.dto.UserAuthDTO;
import git.dimitrikvirik.anychat.model.entity.User;
import git.dimitrikvirik.anychat.repository.UserRepository;
import git.dimitrikvirik.anychat.security.JwtTokenProvider;
import git.dimitrikvirik.anychat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthRestController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> authentication(@RequestBody UserAuthDTO request){
         return userService.authentication(request);
    }
    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response){
        var securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }
}
