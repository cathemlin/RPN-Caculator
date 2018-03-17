package com.company.utils;

import com.company.common.EnumOperator;

/**
 * Created by daxiang on 18-3-16.
 */
public class EnumUtil {
    public static int needNumbers(EnumOperator operator){
        int i=0;
        switch (operator){
            case ADD:
            case SUBTRACT:
            case MULTIPLY:
            case DIVIDED:
                i=2;break;
            case SQRT:
            case UNDO:
                i=1;break;
            case CLEAR:
                i=0;break;
        }
        return i;
    }

}
