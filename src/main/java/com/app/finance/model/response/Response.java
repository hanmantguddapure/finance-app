package com.app.finance.model.response;

import java.util.List;

import com.app.finance.model.Errors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
	private int status;
	private List<Errors> errorList;
	private T response;
	String message;
}
