package Expression.Leaf;

import java.util.Date;

/**
 * Expression.Leaf
 * Create by pmtoan
 * Date 12/26/2022 - 9:07 PM
 * Description: ...
 */
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
