package Expression.Leaf;

import java.util.Date;

/**
 * Expression.Leaf
 * Create by pmtoan
 * Date 12/26/2022 - 9:06 PM
 * Description: ...
 */
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
