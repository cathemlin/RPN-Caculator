package com.company.common;

import com.company.exception.InvalidException;

import java.math.BigDecimal;

import static java.math.BigDecimal.ROUND_HALF_UP;

/**
 * Created by daxiang on 18-3-16.
 */
public class RpnCalc {
    private static final BigDecimal TWO= BigDecimal.valueOf(2);
    private static final int SCALE = 15;

    public static String twoNumsCal(String second, String first, EnumOperator enumOperator){

        BigDecimal firstNum= new BigDecimal(first);
        BigDecimal secondNum= new BigDecimal(second);

        switch (enumOperator){
            case ADD:
                return firstNum.add(secondNum).toString();

            case SUBTRACT:
                return firstNum.subtract(secondNum).toString();

            case MULTIPLY:
                return firstNum.multiply(secondNum).toString();

            case DIVIDED:
                return firstNum.divide(secondNum).toString();
            default:
                return null;
        }
    }

    public static String sqrtCal(String str) {
        BigDecimal A = new BigDecimal(str);
        if(A.compareTo(BigDecimal.ZERO)==-1) {
            return "input value to squre root is invalid";
        }

        BigDecimal x0 = new BigDecimal("0");
        BigDecimal x1 = new BigDecimal(Math.sqrt(A.doubleValue()));
        while (!x0.equals(x1)) {
            x0 = x1;
            x1 = A.divide(x0, SCALE, ROUND_HALF_UP);
            x1 = x1.add(x0);
            x1 = x1.divide(TWO, SCALE, ROUND_HALF_UP);
        }
        return x1.toString();
    }
}