package git.dimitrikvirik.anychat.model.param;


import com.fasterxml.jackson.databind.ObjectMapper;
import git.dimitrikvirik.anychat.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;


import java.beans.Encoder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserParam  {

    private String username;
    private String firstname;
    private String lastname;
    private String password;


    public User toUser(){
        ObjectMapper objectMapper = new ObjectMapper();
       User user = objectMapper.convertValue(this, User.class);
       return  user;
    }


}
