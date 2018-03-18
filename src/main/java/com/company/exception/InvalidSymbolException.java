package com.company.exception;

/**
 * Created by daxiang on 18-3-18.
 */
public class InvalidSymbolException extends  Exception{

    private String msg;
    private String symbol;

    public InvalidSymbolException(String msg, String symbol) {
        super(symbol+":"+msg);
        this.msg = msg;
        this.symbol = symbol;
    }

    public String getMsg() {
        return msg;
    }

    public String getSymbol() {
        return symbol;
    }
}
