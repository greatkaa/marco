package online.greatlab.rpn;

import java.util.List;
import java.util.Map;

/**
 * @author a.kotov
 * @since 21.06.2018
 */
public interface OperationStrategy<T> {
    T execute(Map<String, String> valueMap, String... params);
}
