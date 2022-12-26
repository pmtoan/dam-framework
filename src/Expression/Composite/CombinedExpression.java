package Expression.Composite;

import Expression.IExpression;

/**
 * Expression.Composite
 * Create by pmtoan
 * Date 12/26/2022 - 5:06 PM
 * Description: ...
 */
public abstract class CombinedExpression implements IExpression {
    protected IExpression left;
    protected IExpression right;

    protected CombinedExpression(IExpression left, IExpression right){
        this.left = left;
        this.right = right;
    }

    public abstract String getOperator();

    @Override
    public String toString() {
        return "(" + this.left + getOperator() + this.right + ")";
    }
}
