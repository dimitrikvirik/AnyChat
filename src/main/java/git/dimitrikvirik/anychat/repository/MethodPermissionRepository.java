package git.dimitrikvirik.anychat.repository;

import git.dimitrikvirik.anychat.model.entity.MethodPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MethodPermissionRepository extends JpaRepository<MethodPermission, Long> {
}