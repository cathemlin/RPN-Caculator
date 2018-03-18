package com.company.utils;

import com.company.common.EnumOperator;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by daxiang on 18-3-16.
 */
public class SymbolUtil {

    public static boolean isOperator(String str){
        return EnumOperator.getOperator(str)==null? false:true;
    }

    public static boolean isNumber(String str){
        Pattern pattern = Pattern.compile("-?[0-9]+.?[0-9]*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    public static boolean isValidSymbol(String str){
        return isNumber(str)||isOperator(str);
    }

    public static String getStackContent(Stack stack){
        StringBuilder stackTrace= new StringBuilder();
        for (int sz=stack.size()-1; sz >= 0; --sz) {
            stackTrace.append(stack.get(sz)).append(" ");
        }
        return "The current content of stack is : "+stackTrace.toString().trim();
    }
}
