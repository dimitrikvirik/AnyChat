package git.dimitrikvirik.anychat.security;

import git.dimitrikvirik.anychat.Exception.RecordNotFoundException;
import git.dimitrikvirik.anychat.model.entity.User;
import git.dimitrikvirik.anychat.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(
                        () ->  new RecordNotFoundException(String.format("User with username %s not found!", username)));
        return SecurityUser.fromUser(user);
    }
}
