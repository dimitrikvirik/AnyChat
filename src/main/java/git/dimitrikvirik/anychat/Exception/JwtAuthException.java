package git.dimitrikvirik.anychat.Exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
@Getter
public class JwtAuthException extends AuthenticationException {
    private HttpStatus httpStatus;

    public JwtAuthException(String msg, HttpStatus unauthorized) {
        super(msg);
    }

    public JwtAuthException(String msg) {
        super(msg);
    }
}
