package com.company;

import com.company.common.EnumOperator;
import com.company.exception.InSufficientNumberException;
import com.company.exception.InvalidSymbolException;
import com.company.utils.RpnMath;
import com.company.utils.SymbolUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ){

        Stack<String> stack= new Stack<String>();
        Stack<String> undoStack= new Stack<String>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int index=0;
        try {
            String inputStr=null;
            String[] inputArr=null;
            List<String> inputList=null;
            String arr=null;
            System.out.println("Please input your String!");
            while(true){
                inputStr=br.readLine();
                if(inputStr==null ||inputStr.contains("$")){
                    break;//terminate
                }

                inputArr = inputStr.trim().split(" ");
                //get rid of empty string
                inputList=Arrays.asList(inputArr).stream().filter(i-> !i.trim().isEmpty()).collect(Collectors.toList());
                for (index=0; index < inputList.size(); ++index) {
                    arr= inputList.get(index);
                    if (!SymbolUtil.isValidSymbol(arr))
                        throw new InvalidSymbolException("Invalid symbol:", arr);

                    if (SymbolUtil.isNumber(arr)) {
                        RpnMath.pushNumber(stack, undoStack, arr);
                    } else if (SymbolUtil.isOperator(arr)) {
                        EnumOperator enumOperator = EnumOperator.getOperator(arr);
                        int i = enumOperator.getNeedNumbers();

                        if (i == 2) {
                            RpnMath.binaryOperation(stack, undoStack, enumOperator);
                        } else if (enumOperator == EnumOperator.SQRT) {
                            RpnMath.sqrtOperation(stack, undoStack, enumOperator);
                        } else if (enumOperator == EnumOperator.CLEAR) {
                            RpnMath.clearOperation(stack, undoStack);
                        } else if (enumOperator == EnumOperator.UNDO) {
                            RpnMath.undoOperation(stack, undoStack);
                        }
                    }

                }
                System.out.println(SymbolUtil.getStackContent(stack));
            }
        }catch(InvalidSymbolException ex){
            System.out.println(ex.getMsg() + ex.getSymbol());
        }catch(InSufficientNumberException ex){
            int position = 2 * index + 1;//including the space between input strings
            System.out.println("operator <" + ex.getOperator() + "> (position:<" + position + ">):insufficient numbers");
        }catch (IOException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }

        System.out.println(SymbolUtil.getStackContent(stack));
    }

}
