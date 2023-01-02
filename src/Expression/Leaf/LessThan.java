package Expression.Leaf;

import java.util.Date;

public class LessThan extends BinaryOperator{
    public LessThan(String fieldName, String value) {
        super(fieldName, value);
    }

    public LessThan(String fieldName, int value) {
        super(fieldName, value);
    }

    public LessThan(String fieldName, float value) {
        super(fieldName, value);
    }

    public LessThan(String fieldName, double value) {
        super(fieldName, value);
    }

    public LessThan(String fieldName, Date value) {
        super(fieldName, value);
    }

    public LessThan(String fieldName, boolean value) {
        super(fieldName, value);
    }

    @Override
    protected String getOperator() {
        return " < ";
    }
}
