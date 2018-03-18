package com.company.common;

import com.company.exception.InvalidSymbolException;
import java.math.BigDecimal;
import static java.math.BigDecimal.ROUND_HALF_UP;

/**
 * Created by daxiang on 18-3-16.
 */
public class DecimalCalc {
    private static final BigDecimal TWO= BigDecimal.valueOf(2);
    private static final int SCALE = 15;
    private static final int SCALE2 =SCALE+1;

    /**
     * binary math operation
     * @param second
     * @param first
     * @param enumOperator
     * @return
     * @throws Exception
     */
    public static String twoNumsCal(String second, String first, EnumOperator enumOperator) throws Exception{

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
                return firstNum.divide(secondNum,SCALE,ROUND_HALF_UP).toString();
            default:
                throw new Exception("Invalid Exception operator");
        }
    }

    /**
     * Babylonian method(巴比伦算法) X=1/2*(x+S/x) iterate until reach the precision
     * supposed that Double.Max_Value is large enough for input param.
     * @param str
     * @return
     * @throws Exception
     */
    public static String sqrtCal(String str) throws Exception {
        BigDecimal S = new BigDecimal(str);
        if(S.compareTo(BigDecimal.ZERO)==-1) {
            throw new InvalidSymbolException("Invalid Operation: square root on the ",str);
        }

        BigDecimal x0 = new BigDecimal("0");
        BigDecimal x1 = new BigDecimal(Math.sqrt(S.doubleValue()));

        while (!x0.equals(x1)) {
            x0 = x1;
            x1 = S.divide(x0, SCALE2, ROUND_HALF_UP);
            x1 = x1.add(x0);
            x1 = x1.divide(TWO, SCALE2, ROUND_HALF_UP);
        }
        return x1.setScale(SCALE,ROUND_HALF_UP).toString();
    }
}