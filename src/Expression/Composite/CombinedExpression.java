package Expression.Composite;

import Expression.IExpression;

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
