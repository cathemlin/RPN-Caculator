package com.company.exception;

/**
 * Created by daxiang on 18-3-16.
 */
public class InvalidException extends Exception{
    public InvalidException(String msg){super(msg);}
    public InvalidException(String msg, Throwable cause){super(msg,cause);}


}
