package Expression.Leaf;

import java.util.Date;

/**
 * Expression.Leaf
 * Create by pmtoan
 * Date 12/26/2022 - 8:56 PM
 * Description: ...
 */
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
