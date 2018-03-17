package com.company;

import com.company.common.EnumOperator;
import com.company.common.RpnCalc;
import com.company.utils.EnumUtil;
import com.company.utils.StringOperatorUtil;

import java.util.Stack;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ){


        String argExpression="-4 sqrt ";
        String[] array= argExpression.trim().split(" ");

        Stack<String> stack= new Stack<String>();
        Stack<String> undoStack= new Stack<String>();
        final String undoSingle="undoSingle";
        final String undoRet1= "undoRet1";//calc needs one number;
        final String undoRet2= "undoRet2";//calc needs two numbers;
        String str1=null, str2=null;
        for(String arr: array) {
            if(StringOperatorUtil.isNumber(arr)){
                stack.push(arr);
                undoStack.push(undoSingle);
            }else if (StringOperatorUtil.isOperator(arr)){
                EnumOperator enumOperator= EnumOperator.getOperator(arr);
                int i= EnumUtil.needNumbers(enumOperator);

                if(i==2){
                    undoStack.push(enumOperator.getStrCode());
                    stack.push(RpnCalc.twoNumsCal(undoStack.push(stack.pop()),undoStack.push(stack.pop()),enumOperator));
                    undoStack.push(undoRet2);
                }else if(enumOperator==EnumOperator.SQRT){
                    undoStack.push(enumOperator.getStrCode());
                    undoStack.push(stack.pop());
                    stack.push(RpnCalc.sqrtCal(undoStack.peek()));
                    undoStack.push(undoRet1);
                }else if(enumOperator==EnumOperator.CLEAR){
                    stack.clear();
                    undoStack.clear();
                }else if(enumOperator==EnumOperator.UNDO){

                    if(undoSingle.equals(undoStack.peek())){//undo one number
                        undoStack.pop();
                        stack.pop();
                    }else if(undoRet2.equals(undoStack.peek())){//undo one operator needs tow numbers
                        stack.pop();//pop out the result
                        undoStack.pop();//pop out undoRet2 tag
                        stack.push(undoStack.pop());//push back the first number
                        stack.push(undoStack.pop());//push back the second number
                        undoStack.pop();//pop out the operator
                    }else if(undoRet1.equals(undoStack.peek())){//undo one operator needs one numbers
                        stack.pop();//pop out the result
                        undoStack.pop();//pop out undoRet1 tag
                        stack.push(undoStack.pop());//push back the number
                        undoStack.pop();//pop out the operator
                    }

                }

            }else {
                //非法符号
                System.out.println("Warning! invalid input string: "+ arr );
                System.out.println(StringOperatorUtil.getStackContent(stack));
                break;
            }

            System.out.println(StringOperatorUtil.getStackContent(stack));
        }
    }
}
