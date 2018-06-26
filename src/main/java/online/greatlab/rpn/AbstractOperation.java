package online.greatlab.rpn;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/**
 * @author a.kotov
 * @since 21.06.2018
 */
@Data
@AllArgsConstructor
public abstract class AbstractOperation<T> implements OperationStrategy<T>{
    private String name;
    private boolean leftAssociative;
    private int priority;

    protected String[] parseValues(Map<String, String> valueMap, String[] params) {
        for (int i = 0; i < params.length; i++) {
            if (valueMap.containsKey(params[i])) {
                params[i] = valueMap.get(params[i]);
            }
        }
        return params;
    }

}
