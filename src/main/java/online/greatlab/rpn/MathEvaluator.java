package online.greatlab.rpn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author a.kotov
 * @since 21.06.2018
 */
public class MathEvaluator extends AbstractEvaluator<Double> {

    public MathEvaluator() {
        super();
        operations.putAll(OperationsUtil.getMathOperations());
        operations.putAll(OperationsUtil.getMathFunctions());
    }

    @Override
    public Double evaluate(List<String> rpn, Map<String, String> values) {
        return Double.valueOf(evaluateExpression(rpn, values));
    }

}
