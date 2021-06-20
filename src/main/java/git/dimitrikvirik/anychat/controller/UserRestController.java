package git.dimitrikvirik.anychat.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import git.dimitrikvirik.anychat.model.entity.User;
import git.dimitrikvirik.anychat.model.param.UserDetailsForm;
import git.dimitrikvirik.anychat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;


@RequestMapping("/user")
@RestController
public class UserRestController {


    @Autowired
    UserService userService;
    

    @PreAuthorize("hasRole('developerero')")
    @GetMapping("/check/dev")
    public String checkForDeveloper(){
        return "Checking... For Developer";
    }




    @GetMapping
    public Map currentUser(Principal principal) {
        try {
            User user = userService.getCurrentUser(principal.getName());
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.convertValue(user, Map.class);
        }catch (Exception e){
            return  null;
        }
    }
    @PostMapping("/create")
    public String createuser(@RequestBody UserDetailsForm userDetailsForm, Principal principal){
        userService.create(principal.getName(), userDetailsForm);
        return  "success";
    }



}
