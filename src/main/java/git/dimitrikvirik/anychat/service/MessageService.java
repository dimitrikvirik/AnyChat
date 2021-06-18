package git.dimitrikvirik.anychat.service;

import git.dimitrikvirik.anychat.model.entity.Message;
import git.dimitrikvirik.anychat.model.entity.User;
import git.dimitrikvirik.anychat.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
}


