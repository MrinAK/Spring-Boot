package eu.itdifferentcources.internetprovider.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNameNotFound extends RuntimeException {

    public UserNameNotFound(String message) {
        super(message);
    }
}
