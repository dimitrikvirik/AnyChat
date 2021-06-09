package git.dimitrikvirik.anychat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Permission {
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    USER_EDIT("user:edit"),
    USER_DELETE("user:delete");
    private final String permission;
}
