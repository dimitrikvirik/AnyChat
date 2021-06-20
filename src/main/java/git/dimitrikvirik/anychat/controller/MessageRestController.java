package git.dimitrikvirik.anychat.controller;

import git.dimitrikvirik.anychat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/message")
public class MessageRestController {

    @Autowired
    MessageService messageService;

    @PreAuthorize("hasRole('user')")
    @PostMapping("/send")
    public String send(@RequestBody String text,  Principal principal){
        messageService.send(text, principal.getName());

        return "success";
    }


}
