package Expression.Leaf;

import java.util.Date;

public class Equal extends BinaryOperator{
    public Equal(String fieldName, String value) {
        super(fieldName, value);
    }

    public Equal(String fieldName, int value) {
        super(fieldName, value);
    }

    public Equal(String fieldName, float value) {
        super(fieldName, value);
    }

    public Equal(String fieldName, double value) {
        super(fieldName, value);
    }

    public Equal(String fieldName, Date value) {
        super(fieldName, value);
    }

    public Equal(String fieldName, boolean value) {
        super(fieldName, value);
    }

    @Override
    protected String getOperator() {
        return " = ";
    }
}
