package com.company.common;

/**
 * Created by daxiang on 18-3-16.
 */
public enum  EnumOperator {


    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("*"),
    DIVIDED("/"),
    SQRT("sqrt"),
    UNDO("undo"),
    CLEAR("clear");

    private String strCode;

    public String getStrCode() {
        return strCode;
    }

    public void setStrCode(String strCode) {
        this.strCode = strCode;
    }

    EnumOperator(String strCode) {
        this.strCode = strCode;
    }

    public static EnumOperator getOperator(String str){

        for(EnumOperator enumOperator: EnumOperator.values()){
            if(enumOperator.getStrCode().equals(str))
                return enumOperator;
        }
        return null;
    }

}
