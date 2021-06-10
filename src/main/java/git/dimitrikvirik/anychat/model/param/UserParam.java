package git.dimitrikvirik.anychat.model.param;

import git.dimitrikvirik.anychat.model.dto.UserDTO;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserParam extends UserDTO {
    public String password;
}
