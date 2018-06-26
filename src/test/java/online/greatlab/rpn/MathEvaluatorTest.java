package online.greatlab.rpn;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author a.kotov
 * @since 26.06.2018
 */
public class MathEvaluatorTest {
    @Test
    public void evaluate() throws Exception {
        String[] expression3 = new String[] {"2", "^", "3", "*", "(", "12", "/", "6", ")", "+", "18", "/", "3", "+", "5.0", "/", "2" };
        System.out.println(Arrays.asList(expression3));
        List<String> convert3 = InfixConverter.convert(expression3);
        System.out.println(convert3);
        System.out.println("[2, 3, ^, 12, 6, /, *, 18, 3, /, +, 5.0, 2, /, +]");
        MathEvaluator mathEvaluator = EvaluatorFactory.getMathEvaluator();
        double evaluatorResult = mathEvaluator.evaluate(convert3);
        assertEquals(24.5, evaluatorResult, 0);
    }

}