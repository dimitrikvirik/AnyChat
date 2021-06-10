package git.dimitrikvirik.anychat.repository;

import git.dimitrikvirik.anychat.model.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}