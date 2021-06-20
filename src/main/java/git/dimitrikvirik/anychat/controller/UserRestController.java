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
    public Map currentUser(Principal principal) throws Exception {
            User user = userService.getCurrentUser(principal.getName());
            ObjectMapper objectMapper = new ObjectMapper();
         return  objectMapper.convertValue(user, Map.class);
    }
    @PostMapping("/create")
    public String createuser(@RequestBody UserDetailsForm userDetailsForm, Principal principal){
        userService.create(principal.getName(), userDetailsForm);
        return  "success";
    }



}
