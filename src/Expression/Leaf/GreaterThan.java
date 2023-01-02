package Expression.Leaf;

import java.util.Date;

public class GreaterThan extends BinaryOperator{
    public GreaterThan(String fieldName, String value) {
        super(fieldName, value);
    }

    public GreaterThan(String fieldName, int value) {
        super(fieldName, value);
    }

    public GreaterThan(String fieldName, float value) {
        super(fieldName, value);
    }

    public GreaterThan(String fieldName, double value) {
        super(fieldName, value);
    }

    public GreaterThan(String fieldName, Date value) {
        super(fieldName, value);
    }

    public GreaterThan(String fieldName, boolean value) {
        super(fieldName, value);
    }

    @Override
    protected String getOperator() {
        return " > ";
    }
}
