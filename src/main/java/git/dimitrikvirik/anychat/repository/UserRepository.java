package git.dimitrikvirik.anychat.repository;

import git.dimitrikvirik.anychat.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query( "select o from User  o where o.K_id=:id" )
    Optional<User> findByKid(String id);
}