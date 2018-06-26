package online.greatlab.rpn.operator.logic;

import online.greatlab.rpn.AbstractOperation;

import java.util.Map;

/**
 * @author a.kotov
 * @since 21.06.2018
 */

public class EqualsOperator extends AbstractOperation<Boolean> {

    public EqualsOperator() {
        super("==", true, 2);
    }

    @Override
    public Boolean execute(Map<String, String> valueMap, String... params) {
        params = parseValues(valueMap, params);
        double value1 = Double.parseDouble(params[0]);
        double value2 = Double.parseDouble(params[1]);
        return value1 == value2;
    }
}
