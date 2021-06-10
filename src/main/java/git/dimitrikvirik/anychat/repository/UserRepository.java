package git.dimitrikvirik.anychat.repository;

import git.dimitrikvirik.anychat.model.entity.Role;
import git.dimitrikvirik.anychat.model.entity.Status;
import git.dimitrikvirik.anychat.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    @Modifying
    @Query("update User u set u.firstname = ?1, u.lastname = ?2, u.password = ?3, u.age = ?4, u.editDate = ?5  where u.username = ?6")
     void setUserByUsername(String firstname, String lastname, String password, Integer age, Date editDate, String username);
    @Modifying
    @Query("update User u set u.status = ?1 where u.username = ?2")
    void setStatusByUsername(Status status, String username);
    @Modifying
    @Query("update User u set u.role = ?1 where u.username = ?2")
    void setRoleByUsername(Role role, String username);

    void deleteByUsername(String username);
}