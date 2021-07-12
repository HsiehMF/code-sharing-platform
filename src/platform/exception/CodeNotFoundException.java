package platform.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Please Enter a Valid Code")  // 404
public class CodeNotFoundException extends RuntimeException {
}