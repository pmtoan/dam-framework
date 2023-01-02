package Expression.Leaf;

import java.util.Date;

public class GreaterOrEqual extends BinaryOperator{
    public GreaterOrEqual(String fieldName, String value) {
        super(fieldName, value);
    }

    public GreaterOrEqual(String fieldName, int value) {
        super(fieldName, value);
    }

    public GreaterOrEqual(String fieldName, float value) {
        super(fieldName, value);
    }

    public GreaterOrEqual(String fieldName, double value) {
        super(fieldName, value);
    }

    public GreaterOrEqual(String fieldName, Date value) {
        super(fieldName, value);
    }

    public GreaterOrEqual(String fieldName, boolean value) {
        super(fieldName, value);
    }

    @Override
    protected String getOperator() {
        return " >= ";
    }
}
