package online.greatlab.rpn;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author a.kotov
 * @since 26.06.2018
 */
public class LogicEvaluatorTest {

    @Test
    public void evaluate() {
        LogicEvaluator evaluator = EvaluatorFactory.getLogicEvaluator();

        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("BLABLA", "4");
        valuesMap.put("EXTRUDER_1", null);
        boolean evaluateResult = evaluator.evaluate(Arrays.asList("2", "BLABLA", "max", "4", "==", "5", "5", "==", "&&", "EXTRUDER_1", "ENABLE", "&&"), valuesMap);
        assertEquals(true, evaluateResult);
    }

}