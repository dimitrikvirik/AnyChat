package git.dimitrikvirik.anychat.service;

import git.dimitrikvirik.anychat.repository.MethodPermissionRepository;
import git.dimitrikvirik.anychat.repository.PermissionRepository;
import git.dimitrikvirik.anychat.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PermissionService {

    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;
    private final MethodPermissionRepository methodPermissionRepository;
    private final AuthenticationManager authenticationManager;

    boolean isAllowed(String methodName){
        return true;
    }
}
