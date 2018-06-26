package online.greatlab.rpn.function;

import lombok.Data;
import online.greatlab.rpn.AbstractOperation;

/**
 * @author a.kotov
 * @since 21.06.2018
 */
@Data
public abstract class AbstractFunction<T> extends AbstractOperation<T> {
    private int paramCount;

    public AbstractFunction(String name, boolean leftAssociative, int paramCount) {
        super(name, leftAssociative, 5);
        this.paramCount = paramCount;
    }
}
