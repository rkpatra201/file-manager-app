package com.file.app.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppErrorHandlerAdvice {

	private final Logger logger=LoggerFactory.getLogger(AppErrorHandlerAdvice.class);
	@ExceptionHandler(Throwable.class)
	public String handleThrowable(Throwable e)
	{
		logger.error(e.toString(),e);
		return e.getMessage();
	}
}
