package git.dimitrikvirik.anychat.Exception;

import org.aspectj.weaver.patterns.ReferencePointcut;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;


public class RecordExceptionHandler  {
    @ExceptionHandler(RecordException.class)
    public ResponseEntity handleException(RecordException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());

    }
}
