package Expression.Leaf;

import Expression.IExpression;

import java.util.Date;

/**
 * Expression.Leaf
 * Create by pmtoan
 * Date 12/26/2022 - 5:12 PM
 * Description: ...
 */
public abstract class BinaryOperator implements IExpression {
    protected String fieldName;
    protected String value;

    private void setFieldName(String fieldName){
        this.fieldName = fieldName;
    }

    protected BinaryOperator(String fieldName, String value){
        setFieldName(fieldName);
        this.value = "'" + value + "'";
    }

    protected BinaryOperator(String fieldName, int value){
        setFieldName(fieldName);
        this.value = String.valueOf(value);
    }

    protected BinaryOperator(String fieldName, float value){
        setFieldName(fieldName);
        this.value = String.valueOf(value);
    }

    protected BinaryOperator(String fieldName, double value){
        setFieldName(fieldName);
        this.value = String.valueOf(value);
    }

    protected BinaryOperator(String fieldName, Date value){
        setFieldName(fieldName);
        this.value = "'" + value.toString() + "'";
    }

    protected BinaryOperator(String fieldName, boolean value){
        setFieldName(fieldName);
        this.value = String.valueOf(value);
    }

    protected abstract String getOperator();

    @Override
    public String toString() {
        return this.fieldName + getOperator() + value;
    }
}
