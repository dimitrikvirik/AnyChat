package git.dimitrikvirik.anychat.service;

import git.dimitrikvirik.anychat.Exception.RecordAlreadyExistException;
import git.dimitrikvirik.anychat.Exception.RecordException;
import git.dimitrikvirik.anychat.Exception.RecordNotFoundException;
import git.dimitrikvirik.anychat.config.SecurityConfig;
import git.dimitrikvirik.anychat.model.dto.UserAuthDTO;
import git.dimitrikvirik.anychat.model.entity.User;
import git.dimitrikvirik.anychat.repository.UserRepository;
import git.dimitrikvirik.anychat.security.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserService {



    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;


    public ResponseEntity<?> authentication(@RequestBody UserAuthDTO request){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            User user = userRepository
                    .findByUsername(request.getUsername())
                    .orElseThrow( ()-> new RecordNotFoundException("User not found"));
            String token = jwtTokenProvider.createToken(request.getUsername(), user.getRole().name());
            Map<Object, Object> response =  new HashMap<>();
            response.put("username", request.getUsername());
            response.put("token", token);
            return ResponseEntity.ok(response);
        }
        catch (AuthenticationException | RecordNotFoundException e){
            return new ResponseEntity<>("Invalid email/password combination", HttpStatus.FORBIDDEN);
        }
    }




    @Transactional(dontRollbackOn = RecordAlreadyExistException.class)
     public User create(User user) throws RecordException {
        try {
            user.setPassword(SecurityConfig.passwordEncoder().encode(user.getPassword()));
            userRepository.save(user);
        }catch (DataIntegrityViolationException e){
            if(Objects.requireNonNull(e.getMessage()).contains("c_username_unique"))
            throw new RecordAlreadyExistException(String.format("User with username %s already exist!", user.getUsername()));
            else throw e;
        }
        return null;
    }


    public List<User> getAll() {
       return userRepository.findAll();
    }

    public User getById(int id) throws RecordNotFoundException {
        var optionalUser = userRepository.findById(id);
       if(optionalUser.isPresent()){
         return   optionalUser.get();
       }
       else throw new RecordNotFoundException(String.format("User with id %s not found!", id));
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }
}
