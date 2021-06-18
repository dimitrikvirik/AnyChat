package git.dimitrikvirik.anychat.service;

import git.dimitrikvirik.anychat.model.entity.User;
import git.dimitrikvirik.anychat.model.param.UserDetailsForm;
import git.dimitrikvirik.anychat.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getCurrentUser(String id) throws Exception {
        Optional<User> optionalUser =  userRepository.findByKid(id);
        if(optionalUser.isPresent()){
            return optionalUser.get();
        }
        else{
            throw new Exception("User not found");
        }
    }
    public User create(String id, UserDetailsForm userDetailsForm){
        User user = new User();
        if(!userRepository.findByKid(id).isPresent()) {
            user.setK_id(id);
            user.setCity(userDetailsForm.getCity());
            user.setCountry(userDetailsForm.getCountry());
            user.setPhone(userDetailsForm.getPhone());
            userRepository.save(user);
        }
     return  null;
    }

}
