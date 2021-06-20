package git.dimitrikvirik.anychat.facade;

import com.fasterxml.jackson.databind.ObjectMapper;
import git.dimitrikvirik.anychat.model.dto.MessageDTO;
import git.dimitrikvirik.anychat.model.entity.Message;
import git.dimitrikvirik.anychat.model.entity.User;
import git.dimitrikvirik.anychat.service.MessageService;
import git.dimitrikvirik.anychat.service.UserService;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MessageFacade {
    @Autowired
    UserService userService;
    @Autowired
    MessageService messageService;
    @Autowired
    Keycloak keycloak;

    public List<Map<String, Object>> getAll() throws Exception {
      List<Message> messageList = messageService.getAll();
      List<Map<String,Object>> messages = new ArrayList<>();
        ObjectMapper objectMapper=  new ObjectMapper();
        for (Message message : messageList) {

            UsersResource usersResource = keycloak.realm("appsdeveloperblog").users();
              String KId = message.getUser().getK_id();
            UserResource userResource = usersResource.get(KId);
            Map<String, Object> map = new HashMap<>();
            map.put("author", userResource.toRepresentation().getEmail());
            map.put("text",message.getText());
            map.put("createDate",message.getCreateDate());
            map.put("message_id", message.getId());
            messages.add(map);
        }

      return messages;
    }
}
