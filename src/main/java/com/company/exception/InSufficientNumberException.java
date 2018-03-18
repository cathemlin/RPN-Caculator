package com.company.exception;

/**
 * Created by daxiang on 18-3-18.
 */
public class InSufficientNumberException extends Exception {

    private String operator;


    public InSufficientNumberException(String operator) {
        this.operator = operator;
    }

    public InSufficientNumberException(String message, String operator) {
        super(message);
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }
}
