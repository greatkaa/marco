package online.greatlab.rpn;

import online.greatlab.rpn.function.AbstractFunction;

import java.util.*;

/**
 * @author a.kotov
 * @since 21.06.2018
 */
public class LogicEvaluator extends AbstractEvaluator<Boolean> {

    public LogicEvaluator() {
        super();
        operations.putAll(OperationsUtil.getLogicOperations());
        operations.putAll(OperationsUtil.getMathOperations());
        operations.putAll(OperationsUtil.getMathFunctions());
        operations.putAll(OperationsUtil.getLogicFunctions());
    }

    @Override
    public Boolean evaluate(List<String> rpn, Map<String, String> values) {
        return Boolean.valueOf(evaluateExpression(rpn, values));
    }
}
