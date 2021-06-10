package git.dimitrikvirik.anychat.repository;

import git.dimitrikvirik.anychat.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role getByName(String name);
}