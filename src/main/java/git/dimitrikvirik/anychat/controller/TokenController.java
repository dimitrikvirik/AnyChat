package git.dimitrikvirik.anychat.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount;
import org.keycloak.representations.AccessToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/token")
public class TokenController {

    @GetMapping
    public Map getToken(){
        SimpleKeycloakAccount Details = (SimpleKeycloakAccount) SecurityContextHolder.getContext().getAuthentication().getDetails();
        KeycloakPrincipal keycloakPrincipal =  (KeycloakPrincipal) Details.getPrincipal();
        ObjectMapper objectMapper = new ObjectMapper();
        var token = keycloakPrincipal.getKeycloakSecurityContext().getToken();
       var Map = objectMapper.convertValue(token, Map.class);

        System.out.println(Map);
     return Map;

    }
}
