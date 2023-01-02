package Expression.Composite;

import Expression.IExpression;

public class AndExpression extends CombinedExpression{
    public AndExpression(IExpression left, IExpression right) {
        super(left, right);
    }

    @Override
    public String getOperator() {
        return " AND ";
    }
}
