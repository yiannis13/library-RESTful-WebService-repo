package gr.ioannidis.library.rs.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import gr.ioannidis.library.rs.exception.model.ErrorMessage;

@ControllerAdvice
public class GlobalExceptionMapper {

	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<ErrorMessage> resourseNotFoundExceptionHandler(ResourceNotFoundException ex) {
		ErrorMessage errorMsg = new ErrorMessage(ex.getMessage(), 404, "Please visit: http://myWebsite.com/solutions");
		return new ResponseEntity<ErrorMessage>(errorMsg, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = BadRequestException.class)
	public ResponseEntity<ErrorMessage> badRequestExceptionHandler(BadRequestException ex) {
		ErrorMessage errorMsg = new ErrorMessage(ex.getMessage(), 400, "Please visit: http://myWebsite.com/solutions");
		return new ResponseEntity<ErrorMessage>(errorMsg, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = AccessNotAllowedException.class)
	public ResponseEntity<ErrorMessage> accessNotAllowsExceptionhandler(AccessNotAllowedException ex) {
		ErrorMessage errorMsg = new ErrorMessage(ex.getMessage(), 401 , "Please visit: http://myWebsite.com/solutions");
		return new ResponseEntity<ErrorMessage>(errorMsg, HttpStatus.UNAUTHORIZED);
	}
	
}
