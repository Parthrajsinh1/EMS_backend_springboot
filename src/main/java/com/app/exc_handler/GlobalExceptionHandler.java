package com.app.exc_handler;

import java.util.Map;
import java.util.stream.Collectors;
import com.app.custome_exceptions.*;
import com.app.dto.ApiResponse;
import java.lang.RuntimeException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice//MANDATORY cls lvl annoatation to tell SC : following is global exc handler class -- 
				//to advise all controllers(RestController), regarding exc handling
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	//exc handling methods.
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		System.out.println("in handle method arg not valid !!!!");
		Map<String, String> collect = ex.getBindingResult().getFieldErrors().stream() //Stream<FieldErrors>
			.collect(Collectors.toMap(f->f.getField(), FieldError::getDefaultMessage)); //we can make method ref to any lambda exp , so here im using both whichever you like
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(collect);	//both method ref -> .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
	}
	//how to tell SC ,that following is exc handling method 
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e){
		System.out.println("in handle res not found...");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
	}
	//for all remaining excs : add global exc handling method
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> handleRuntimeException(RuntimeException e){
	System.out.println("in handle runtime exc ");
	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage()));
	}
}
