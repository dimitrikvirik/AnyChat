package git.dimitrikvirik.anychat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import git.dimitrikvirik.anychat.model.entity.User;
import git.dimitrikvirik.anychat.model.param.UserDetailsForm;
import git.dimitrikvirik.anychat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;


@RequestMapping("/user")
@RestController
public class UserRestController {


    @Autowired
    UserService userService;
    

    @PreAuthorize("hasRole('developer')")
    @GetMapping("/check/dev")
    public String checkForDeveloper(){
        return "Checking... For Developer";
    }

    @PreAuthorize("hasRole('user')")
    @GetMapping("/check")
    public String check(Principal principal){
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) principal;

        return    jwtAuthenticationToken.getTokenAttributes().get("email").toString();
    }
    @PreAuthorize("hasRole('user')")
    @GetMapping
    public Map currentUser(Principal principal){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
        return  objectMapper.convertValue(userService.getCurrentUser(principal.getName()), Map.class);
        }catch (Exception e){
            return null;
        }
    }
    @GetMapping("/create")
    public User createUser(@RequestParam String country, @RequestParam String city, @RequestParam String phone,Principal principal){
        UserDetailsForm userDetailsForm = new UserDetailsForm(country, city, phone);
        User user =  userService.create(principal.getName(), userDetailsForm);
        System.out.println(user);
        return user;
    }



}
