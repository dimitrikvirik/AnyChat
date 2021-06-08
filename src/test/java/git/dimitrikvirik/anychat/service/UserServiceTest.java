package git.dimitrikvirik.anychat.service;

import git.dimitrikvirik.anychat.model.entity.User;
import git.dimitrikvirik.anychat.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceTest {


    @Autowired
    @Mock
    UserRepository userRepository;

    @Test
    void create() {
        User user = new User();
        user.setPassword("1234");
        user.setUsername("testU");
        user.setFirstname("testF");
        user.setLastname("testL");

       assertEquals(userRepository.save(user), user);
       userRepository.delete(user);
    }
    @Test
    void createDuplicateException(){
        User user = new User();
        user.setPassword("1234");
        user.setUsername("testU");
        user.setFirstname("testF");
        user.setLastname("testL");
        userRepository.save(user);
        User user1 = new User();
        user1.setPassword("1234");
        user1.setUsername("testU");
        user1.setFirstname("testF");
        user1.setLastname("testL");

        userRepository.save(user1);


    }
}