package git.dimitrikvirik.anychat.facade;

import git.dimitrikvirik.anychat.model.dto.MessageDTO;
import git.dimitrikvirik.anychat.model.entity.Message;
import git.dimitrikvirik.anychat.model.entity.User;
import git.dimitrikvirik.anychat.service.MessageService;
import git.dimitrikvirik.anychat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageFacade {
    @Autowired
    UserService userService;
    @Autowired
    MessageService messageService;
    public List<MessageDTO> getAll() throws Exception {
      List<Message> messageList = messageService.getAll();
      List<MessageDTO> messageDTOS = new ArrayList<>();
      for(int i = 0; i < messageList.size(); i++){
          MessageDTO messageDTO = new MessageDTO();
          messageDTO.setText(messageList.get(i).getText());
          messageDTO.setCreateDate(messageList.get(i).getCreateDate());
       //   messageDTO.setAuthor(userService.getCurrentUser(messageList.get(i).getUser().getK_id()));
      }
      return messageDTOS;
    }
}
