package sit.int204.classicmodelsservice.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Setter
@Getter
public class GeneralException extends ResponseStatusException {
    String title;
    public GeneralException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }
}
