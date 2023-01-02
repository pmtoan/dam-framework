package Expression.Leaf;

import java.util.Date;

public class LessOrEqual extends BinaryOperator{
    public LessOrEqual(String fieldName, String value) {
        super(fieldName, value);
    }

    public LessOrEqual(String fieldName, int value) {
        super(fieldName, value);
    }

    public LessOrEqual(String fieldName, float value) {
        super(fieldName, value);
    }

    public LessOrEqual(String fieldName, double value) {
        super(fieldName, value);
    }

    public LessOrEqual(String fieldName, Date value) {
        super(fieldName, value);
    }

    public LessOrEqual(String fieldName, boolean value) {
        super(fieldName, value);
    }

    @Override
    protected String getOperator() {
        return " <= ";
    }
}
