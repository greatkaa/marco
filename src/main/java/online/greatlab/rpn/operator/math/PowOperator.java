package online.greatlab.rpn.operator.math;

import online.greatlab.rpn.AbstractOperation;

import java.util.Map;

/**
 * @author a.kotov
 * @since 21.06.2018
 */

public class PowOperator extends AbstractOperation<Double> {

    public PowOperator() {
        super("^", false, 5);
    }

    @Override
    public Double execute(Map<String, String> valueMap, String... params) {
        params = parseValues(valueMap, params);
        Double value1 = Double.parseDouble(params[0]);
        Double value2 = Double.parseDouble(params[1]);
        return Math.pow(value1, value2);
    }
}
