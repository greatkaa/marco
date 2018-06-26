package online.greatlab.rpn;

import lombok.Data;
import online.greatlab.rpn.function.AbstractFunction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * @author a.kotov
 * @since 22.06.2018
 */
@Data
public abstract class AbstractEvaluator<T> implements EvaluatorInterface<T> {

    Map<String, AbstractOperation> operations;

    public AbstractEvaluator() {
        this(new HashMap<>());
    }

    public AbstractEvaluator(Map<String, AbstractOperation> operations) {
        this.operations = operations;
    }

    protected String evaluateExpression(List<String> rpn, Map<String, String> valuesMap) {
        Stack<String> opStack = new Stack<>();

        for (String op : rpn) {
            if (isOperator(op)) {
                if (opStack.isEmpty()) {
                    return null;
                }
                AbstractOperation operation = operations.get(op);
                String[] params;
                if (operation instanceof AbstractFunction) {
                    params = getValues(opStack, ((AbstractFunction) operation).getParamCount());
                }
                else {
                    params = getValues(opStack,2);
                }
                if (params == null) {
                    return null;
                }
                Object eval = operations.get(op).execute(valuesMap, params);

                if (eval == null) {
                    return null;
                }
                opStack.push(eval.toString());

            } else {
                opStack.push(op);
            }
        }

        if (opStack.size() != 1) {
            return null;
        }

        return opStack.pop();
    }

    private String[] getValues(Stack<String> opStack, int num) {
        String[] result = new String[num];
        for (int i = num - 1; i >= 0; i--) {
            result[i] = opStack.pop();
            if (result[i] == null) {
                return null;
            }
        }
        return result;
    }

    private boolean isOperator(String op) {
        return operations.containsKey(op);
    }

    @Override
    public T evaluate(List<String> rpn) {
        return evaluate(rpn, new HashMap<>());
    }
}
