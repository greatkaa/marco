package online.greatlab.rpn.operator.math;

import online.greatlab.rpn.AbstractOperation;

import java.util.Map;

/**
 * @author a.kotov
 * @since 21.06.2018
 */

public class MinusOperator extends AbstractOperation<Double> {

    public MinusOperator() {
        super("-", true, 2);
    }

    @Override
    public Double execute(Map<String, String> valueMap, String... params) {
        params = parseValues(valueMap, params);
        Double value1 = Double.parseDouble(params[0]);
        Double value2 = Double.parseDouble(params[1]);
        return value1 - value2;
    }
}
