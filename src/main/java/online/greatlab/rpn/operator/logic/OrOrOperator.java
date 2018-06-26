package online.greatlab.rpn.operator.logic;

import online.greatlab.rpn.AbstractOperation;

import java.util.Map;

/**
 * @author a.kotov
 * @since 21.06.2018
 */

public class OrOrOperator extends AbstractOperation<Boolean> {

    public OrOrOperator() {
        super("||", true, 1);
    }

    @Override
    public Boolean execute(Map<String, String> valueMap, String... params) {
        params = parseValues(valueMap, params);
        Boolean value1 = Boolean.parseBoolean(params[0]);
        Boolean value2 = Boolean.parseBoolean(params[1]);
        return value1 || value2;
    }
}
