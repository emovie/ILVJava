package com.hello.core.exception;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvisor {

	@ExceptionHandler(BindException.class)
	public String processValidationError(BindException e) {
		BindingResult bindingResult = e.getBindingResult();
		
		StringBuilder builder = new StringBuilder();
//		for(FieldError fieldError : bindingResult.getFieldError()) {
//			builder.append("[");
//		}
		
		return "";
	}
	
}
