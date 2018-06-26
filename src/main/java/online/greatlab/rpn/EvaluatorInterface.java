package online.greatlab.rpn;

import java.util.List;
import java.util.Map;

/**
 * @author a.kotov
 * @since 21.06.2018
 */
public interface EvaluatorInterface<T> {

    T evaluate(List<String> rpn);

    T evaluate(List<String> rpn, Map<String, String> values);

}
