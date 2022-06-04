package com.app.finance.model.response;

import java.util.List;

import com.app.finance.model.Errors;

public class Response<T> {
private int status;
private List<Errors> errorList;
private T response;
}
