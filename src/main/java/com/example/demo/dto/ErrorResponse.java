package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ErrorResponse {
	private int status;
	private String message;
	private Date date;

	public ErrorResponse(int status, String message) {
		this.status = status;
		this.message = message;
		this.date = new Date();
	}
}
