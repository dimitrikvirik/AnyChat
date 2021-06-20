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
      List<Map<String,Object>> messageDTOS = new ArrayList<>();
        ObjectMapper objectMapper=  new ObjectMapper();
        for (Message message : messageList) {
            MessageDTO messageDTO = new MessageDTO();
            messageDTO.setText(message.getText());
            messageDTO.setCreateDate(message.getCreateDate());

            UsersResource usersResource = keycloak.realm("appsdeveloperblog").users();
              String KId = message.getUser().getK_id();
            UserResource userResource = usersResource.get(KId);
            messageDTO.setAuthor(userResource.toRepresentation().getEmail());
            Map<String, Object> map = new HashMap<>();
            map.put("author", messageDTO.getAuthor());
            map.put("text", messageDTO.getText());
            map.put("createDate", messageDTO.getCreateDate());
            messageDTOS.add(map);
        }

      return messageDTOS;
    }
}
