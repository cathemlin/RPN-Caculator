package com.company;

import com.company.common.EnumOperator;
import com.company.exception.InSufficientNumberException;
import com.company.exception.InvalidSymbolException;
import com.company.utils.RpnMath;
import com.company.utils.SymbolUtil;

import java.util.Stack;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ){

        String expression="1 2 3 4 5 * * * * undo undo";
        String[] array= expression.trim().split(" ");

        Stack<String> stack= new Stack<String>();
        Stack<String> undoStack= new Stack<String>();
        String arr=null;
        int index=0;
        try {
            for(;index<array.length;++index){
                arr=array[index];
//                if(arr.equals(""))
//                    continue;//get rid of empty string input

                if(!SymbolUtil.isValidSymbol(arr))
                    throw new InvalidSymbolException("Invalid symbol:", arr);

                if (SymbolUtil.isNumber(arr)) {
                    RpnMath.pushNumber(stack,undoStack,arr);
                } else if (SymbolUtil.isOperator(arr)) {
                    EnumOperator enumOperator = EnumOperator.getOperator(arr);
                    int i = enumOperator.getNeedNumbers();

                    if (i == 2) {
                        RpnMath.binaryOperation(stack,undoStack,enumOperator);
                    } else if (enumOperator == EnumOperator.SQRT) {
                        RpnMath.sqrtOperation(stack,undoStack,enumOperator);
                    } else if (enumOperator == EnumOperator.CLEAR) {
                        RpnMath.clearOperation(stack,undoStack);
                    } else if (enumOperator == EnumOperator.UNDO) {
                        RpnMath.undoOperation(stack, undoStack);
                    }
                }

            }
        }catch (InvalidSymbolException ex){
            System.out.println(ex.getMsg()+ex.getSymbol());
        }catch (InSufficientNumberException ex){
            int position= 2*index+1;//including the space between input strings
            System.out.println("operator <"+ex.getOperator()+"> (position:<"+position+">):insufficient numbers");
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(SymbolUtil.getStackContent(stack));
    }
}
