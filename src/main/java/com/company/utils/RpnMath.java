package com.company.utils;

import com.company.common.DecimalCalc;
import com.company.common.EnumOperator;
import com.company.common.UndoTag;
import com.company.exception.InSufficientNumberException;

import java.util.Stack;

/**
 * Created by daxiang on 18-3-18.
 */
public class RpnMath {

    public static void binaryOperation(Stack<String> stack, Stack<String> undoStack, EnumOperator enumOperator) throws Exception{
        if(stack.size() < 2)
            throw new InSufficientNumberException(enumOperator.getStrCode());

        undoStack.push(enumOperator.getStrCode());
        stack.push(DecimalCalc.twoNumsCal(undoStack.push(stack.pop()), undoStack.push(stack.pop()), enumOperator));
        undoStack.push(UndoTag.undoMath2Num);
    }

    public static void pushNumber(Stack<String> stack, Stack<String> undoStack,String arr){
        stack.push(arr);
        undoStack.push(UndoTag.undoNum);
    }


    public static void sqrtOperation(Stack<String> stack, Stack<String> undoStack, EnumOperator enumOperator) throws Exception{
        if(stack.isEmpty())
            throw new InSufficientNumberException(enumOperator.getStrCode());

        String result=DecimalCalc.sqrtCal(stack.peek());//may throw exception

        undoStack.push(enumOperator.getStrCode());
        undoStack.push(stack.pop());
        stack.push(result);
        undoStack.push(UndoTag.undoMath1Num);
    }

    /**
     * clear the stack which also can be "undo" only once
     * @param stack
     * @param undoStack
     */
    public static void clearOperation(Stack<String> stack, Stack<String> undoStack){
        if(!stack.isEmpty()){
            undoStack.push(UndoTag.undoClear);
            while (!stack.isEmpty()) {
                undoStack.push(stack.pop());
            }
            undoStack.push(UndoTag.undoClear);
        }else {
            undoStack.clear();
        }
    }

    /**
     * undo operation
     * @param stack
     * @param undoStack
     * @throws Exception
     */
    public static void undoOperation(Stack<String> stack, Stack<String> undoStack) throws Exception{
        if(undoStack.isEmpty()) {//reset to cleat state
            clearOperation(stack, undoStack);
            return;
        }

        String undotag=undoStack.peek();
        if(UndoTag.undoNum.equals(undotag)){//undo one number
            undoStack.pop();//pop out undoTag
            stack.pop();//pop out number
        } else if (UndoTag.undoMath2Num.equals(undotag)) {//undo one operator needs tow numbers
            stack.pop();//pop out the result
            undoStack.pop();//pop out undo tag
            stack.push(undoStack.pop());//push back the first number
            stack.push(undoStack.pop());//push back the second number
            undoStack.pop();//pop out the operator
        } else if (UndoTag.undoMath1Num.equals(undotag)) {//undo one operator needs one numbers
            stack.pop();//pop out the result
            undoStack.pop();//pop out undoTag
            stack.push(undoStack.pop());//push back the number
            undoStack.pop();//pop out the operator
        }else if (UndoTag.undoClear.equals(undotag)){
            undoStack.pop();//pop out the undoTag
            while (!UndoTag.undoClear.equals(undoStack.peek())){
                stack.push(undoStack.pop());
            }
            undoStack.pop();//pop out undoTag
        }else {
            throw new Exception("Unknow exception");
        }
    }
}

