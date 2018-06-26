package online.greatlab.rpn;

import online.greatlab.rpn.AbstractOperation;
import online.greatlab.rpn.function.logic.EnableOperation;
import online.greatlab.rpn.function.math.MaxOperation;
import online.greatlab.rpn.operator.logic.AndAndOperator;
import online.greatlab.rpn.operator.logic.EqualsOperator;
import online.greatlab.rpn.operator.logic.OrOrOperator;
import online.greatlab.rpn.operator.math.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author a.kotov
 * @since 22.06.2018
 */
public class OperationsUtil {

    public static Map<String, AbstractOperation> getLogicOperations() {
        Map<String, AbstractOperation> operatorMap = new HashMap<>();
        addOperator(operatorMap, new AndAndOperator());
        addOperator(operatorMap, new OrOrOperator());
        addOperator(operatorMap, new EqualsOperator());
        return operatorMap;
    }

    public static Map<String, AbstractOperation> getMathOperations() {
        Map<String, AbstractOperation> operatorMap = new HashMap<>();
        addOperator(operatorMap, new MinusOperator());
        addOperator(operatorMap, new PlusOperator());
        addOperator(operatorMap, new MultiplyOperator());
        addOperator(operatorMap, new DivideOperator());
        addOperator(operatorMap, new PowOperator());
        return operatorMap;
    }

    public static Map<String, AbstractOperation> getMathFunctions() {
        Map<String, AbstractOperation> operatorMap = new HashMap<>();
        addOperator(operatorMap, new MaxOperation());
        return operatorMap;
    }

    public static Map<String, AbstractOperation> getLogicFunctions() {
        Map<String, AbstractOperation> operatorMap = new HashMap<>();
        addOperator(operatorMap, new EnableOperation());
        return operatorMap;
    }

    private static void addOperator(Map<String, AbstractOperation> operatorMap, AbstractOperation operator) {
        operatorMap.put(operator.getName(), operator);
    }

}
