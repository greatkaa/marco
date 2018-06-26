package online.greatlab.rpn.function.logic;

import online.greatlab.rpn.function.AbstractFunction;

import java.util.Map;

/**
 * @author a.kotov
 * @since 21.06.2018
 */
public class EnableOperation extends AbstractFunction<Boolean> {

    public EnableOperation() {
        super("ENABLE", false, 1);
    }

    @Override
    public Boolean execute(Map<String, String> valueMap, String... params) {
        return valueMap.containsKey(params[0]);
    }
}
