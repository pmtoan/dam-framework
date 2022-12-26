package Expression.Composite;

import Expression.IExpression;

/**
 * Expression.Composite
 * Create by pmtoan
 * Date 12/26/2022 - 9:03 PM
 * Description: ...
 */
public class AndExpression extends CombinedExpression{
    public AndExpression(IExpression left, IExpression right) {
        super(left, right);
    }

    @Override
    public String getOperator() {
        return " AND ";
    }
}
