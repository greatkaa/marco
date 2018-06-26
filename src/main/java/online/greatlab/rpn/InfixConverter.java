package online.greatlab.rpn;

import java.util.*;

/**
 * @author a.kotov
 * @since 22.06.2018
 */
public class InfixConverter {

    private static Map<String, AbstractOperation> operations;

    static {
        operations = new HashMap<>();
        operations.putAll(OperationsUtil.getLogicOperations());
        operations.putAll(OperationsUtil.getMathOperations());
        operations.putAll(OperationsUtil.getMathFunctions());
        operations.putAll(OperationsUtil.getLogicFunctions());
    }

    public static List<String> convert(String[] exprTokens) {
        List<String> infix = new ArrayList<>();
        Stack<String> operatorStack = new Stack<>();

        for (String op : exprTokens) {
            op = op.trim();
            if (op.equals(",")) { //remove commas
                continue;
            }
            //if its an operand , simply append to output
            if (!isOperator(op) && !op.matches("[()]")) {
                infix.add(op);
            }
            //if its an operator
            else {
                //if its a left parenthesis then push it to stack
                if (op.equals("(")) {
                    operatorStack.push("(");
                }
                //other wise if it is a right parenthesis then pop the stack untill we see a matching left parenthesis
                else if (op.equals(")")) {
                    while (!operatorStack.peek().equals("(") && !operatorStack.isEmpty()) {
                        infix.add(operatorStack.pop());
                    }

                    //if we do not have a matching left parethesis then it's a malformed expression
                    if (operatorStack.isEmpty() || !operatorStack.peek().equals("(")) {
                        return null;
                    }
                    //otherwise we found a matching left. Just pop it out
                    else {
                        operatorStack.pop();
                    }
                }
                //otherwise its an operator
                else {
                    //keep poping out element from stack and append in output as long as we see a higher precedence operator
                    //in the top of stack
                    while (
                            !operatorStack.isEmpty()
                                    && (
                                    (isLeftAssociative(op) && getPriority(op) <= getPriority(operatorStack.peek()))
                                            || (!isLeftAssociative(op) && getPriority(op) < getPriority(operatorStack.peek()))
                            )
                            ) {
                        infix.add(operatorStack.pop());
                    }
                    //ow add the operator
                    operatorStack.push(op);
                }
            }
        }

        //if there are left over element sin stack then append them in the output
        while (!operatorStack.isEmpty()) {
            infix.add(operatorStack.pop());
        }

        return infix;
    }

    private static boolean isOperator(String op) {
        return operations.containsKey(op);
    }

    private static boolean isLeftAssociative(String op) {
        return operations.get(op).isLeftAssociative();
    }

    private static int getPriority(String op) {
        if (isOperator(op)) {
            return operations.get(op).getPriority();
        }
        return 0;
    }
}
