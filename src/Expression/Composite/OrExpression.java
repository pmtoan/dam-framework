package Expression.Composite;

import Expression.IExpression;

public class OrExpression extends CombinedExpression{
    public OrExpression(IExpression left, IExpression right) {
        super(left, right);
    }

    @Override
    public String getOperator() {
        return " OR ";
    }
}
