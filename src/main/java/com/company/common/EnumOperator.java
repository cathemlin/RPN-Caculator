package com.company.common;

/**
 * Created by daxiang on 18-3-16.
 */
public enum  EnumOperator {

    ADD("+",2),
    SUBTRACT("-",2),
    MULTIPLY("*",2),
    DIVIDED("/",2),
    SQRT("sqrt",1),
    UNDO("undo",1),//depends on the specific undo operation
    CLEAR("clear",0);

    private String strCode;//operation code
    private int needNumbers;//the required numbers of this operation

    public String getStrCode() {
        return strCode;
    }

    public int getNeedNumbers() {
        return needNumbers;
    }


    EnumOperator(String strCode, int needNumbers) {
        this.strCode = strCode;
        this.needNumbers = needNumbers;
    }


    public static EnumOperator getOperator(String str) {
        for(EnumOperator enumOperator: EnumOperator.values()){
            if(enumOperator.getStrCode().equals(str))
                return enumOperator;
        }
        return null;
    }
}
