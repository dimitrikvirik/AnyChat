package git.dimitrikvirik.anychat.service;

import git.dimitrikvirik.anychat.model.entity.Message;
import git.dimitrikvirik.anychat.model.entity.User;
import git.dimitrikvirik.anychat.repository.MessageRepository;
import git.dimitrikvirik.anychat.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    public void send(String text, String Kid){
        var user = userRepository.findByKid(Kid);
        if(user.isPresent()){
            Message message = new Message();
            message.setText(text);
            message.setUser(user.get());
            messageRepository.save(message);
        }
    }
    public List<Message> getAll(){
     return  messageRepository.findAll();
    }
}


