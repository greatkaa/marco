package online.greatlab.rpn;

/**
 * @author a.kotov
 * @since 21.06.2018
 */
public class EvaluatorFactory {

    public static MathEvaluator getMathEvaluator() {
        return new MathEvaluator();
    }

    public static LogicEvaluator getLogicEvaluator() {
        return new LogicEvaluator();
    }
}
