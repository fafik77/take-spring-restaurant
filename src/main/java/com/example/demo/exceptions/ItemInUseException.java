package com.example.demo.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ItemInUseException extends RuntimeException {
	private final Object id;

	public ItemInUseException(Class<?> entityClass, Object id) {
		super(String.format("%s: %s is in use!", entityClass.getSimpleName(), id));
		this.id = id;
	}
}
