package Wrapper;

import ConnectAdapter.ConnectionAdapter;
import Expression.IExpression;
import Expression.Leaf.Equal;
import Expression.Leaf.GreaterOrEqual;
import Statement.StatementAdapter;

import java.sql.ResultSet;
import java.util.List;

/**
 * Wrapper
 * Create by pmtoan
 * Date 12/31/2022 - 12:31 PM
 * Description: ...
 */
public abstract class ORMObject<T> {
    protected final ConnectionAdapter connection;
    protected StatementAdapter statement = null;
    protected final String tableName = "actor";
    protected final String primaryKey = "";

    public ORMObject(ConnectionAdapter connection){
        this.connection = connection;
    }

    public abstract List<T> find();

    public abstract T findOne(IExpression condition);

    public abstract int insert(T object);

    public abstract int update(T object);

    public abstract int delete(T object);

    public abstract int delete(IExpression condition);
}
