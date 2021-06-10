package git.dimitrikvirik.anychat.service;

import git.dimitrikvirik.anychat.Exception.RecordAlreadyExistException;
import git.dimitrikvirik.anychat.Exception.RecordException;
import git.dimitrikvirik.anychat.Exception.RecordNotFoundException;
import git.dimitrikvirik.anychat.config.SecurityConfig;
import git.dimitrikvirik.anychat.model.dto.UserAuthDTO;
import git.dimitrikvirik.anychat.model.entity.Role;
import git.dimitrikvirik.anychat.model.entity.Status;
import git.dimitrikvirik.anychat.model.entity.User;
import git.dimitrikvirik.anychat.repository.RoleRepository;
import git.dimitrikvirik.anychat.repository.StatusRepository;
import git.dimitrikvirik.anychat.repository.UserRepository;
import git.dimitrikvirik.anychat.security.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@AllArgsConstructor
public class UserService {



    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final StatusRepository statusRepository;
    private final JwtTokenProvider jwtTokenProvider;


    @Transactional
    public ResponseEntity<?> authentication(UserAuthDTO request){
        try{
            System.out.println(request);
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            User user = userRepository
                    .findByUsername(request.getUsername())
                    .orElseThrow( ()-> new UsernameNotFoundException("User not found"));
            String token = jwtTokenProvider.createToken(request.getUsername(), user.getRole().getName());
            Map<Object, Object> response =  new HashMap<>();
            response.put("username", request.getUsername());
            response.put("token", token);
            return ResponseEntity.ok(response);
        }
        catch (AuthenticationException e){
            e.printStackTrace();
            return new ResponseEntity<>("Invalid email/password combination", HttpStatus.FORBIDDEN);
        }
    }

    @Transactional(dontRollbackOn = DataIntegrityViolationException.class)
     public User registration(User user) throws RecordException {
        try {
            user.setPassword(SecurityConfig.passwordEncoder().encode(user.getPassword()));
            user.setRegDate(new Date());
            user.setStatus(statusRepository.getByName("ACTIVE"));
            user.setRole(roleRepository.getByName("USER"));
            return userRepository.save(user);
        }catch (DataIntegrityViolationException e){
            if(Objects.requireNonNull(e.getMessage()).contains("c_username_unique"))
            throw new RecordAlreadyExistException(String.format("User with username %s already exist!", user.getUsername()));
            else throw e;
        }
    }
    @Transactional
    public List<User> getAll() {
       return userRepository.findAll();
    }

    @Transactional(dontRollbackOn = DataIntegrityViolationException.class)
    public void setUserStatusByUsername(String username, String status) throws RecordNotFoundException {
        try {
            Status realStatus = statusRepository.getByName(status);
            userRepository.setStatusByUsername(realStatus, username);
        }catch (DataIntegrityViolationException e){
            throw new RecordNotFoundException(String.format("User with username %s not found!", username));
        }
    }
    @Transactional(dontRollbackOn = DataIntegrityViolationException.class)
    public void setUserRoleByUsername(String username, String role) throws RecordNotFoundException {
        try {
            Role realRole = roleRepository.getByName(role);
            userRepository.setRoleByUsername(realRole, username);
        }catch (DataIntegrityViolationException e){
            throw new RecordNotFoundException(String.format("User with username %s not found!", username));
        }
    }

    @Transactional(dontRollbackOn = DataIntegrityViolationException.class)
    public void forceDeleteByUsername(String username) throws RecordNotFoundException {
        try {
            userRepository.deleteByUsername(username);
        }catch (DataIntegrityViolationException e){
            throw new RecordNotFoundException(String.format("User with username %s not found!", username));
        }
    }

    @Transactional(dontRollbackOn = DataIntegrityViolationException.class)
    public void editByUsername(User user, String username) throws RecordNotFoundException {
        try {
         user.setUsername(username);
         user.setPassword(
                    SecurityConfig.passwordEncoder().encode(user.getPassword())
            );
         userRepository.setUserByUsername(
                user.getFirstname(),
                user.getLastname(),
                user.getPassword(),
                user.getAge(),
                new Date(),
                username);
        }
        catch (DataIntegrityViolationException e){
            throw new RecordNotFoundException(String.format("User with username %s not found!", username));
        }
    }

    @Transactional(dontRollbackOn = DataIntegrityViolationException.class)
    public User getByUsername(String username) throws RecordNotFoundException {
        var optionalUser = userRepository.findByUsername(username);
        if(optionalUser.isPresent()){
            return  optionalUser.get();
        }
        else throw new RecordNotFoundException(String.format("User with username %s not found!", username));
    }
}
