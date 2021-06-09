package git.dimitrikvirik.anychat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public enum Role {
    USER(Set.of(Permission.USER_READ, Permission.USER_WRITE)),
    ADMIN(Set.of(Permission.USER_READ, Permission.USER_WRITE, Permission.USER_DELETE));
    private final Set<Permission> permissionSet;

    public Set<SimpleGrantedAuthority> getAuthorizes(){
            return getPermissionSet()
                    .stream()
                    .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                    .collect(Collectors.toSet());
    }
}
