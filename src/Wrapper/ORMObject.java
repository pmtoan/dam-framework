package Wrapper;

import Connection.ConnectionAdapter;
import Expression.IExpression;
import Statement.StatementAdapter;

import java.util.List;

public abstract class ORMObject<T> {
    protected final ConnectionAdapter connection;
    protected StatementAdapter statement = null;
    protected final String tableName = "actor";
    protected final String primaryKey = "";

    protected final Class<T> typeParameterClass;

    public ORMObject(ConnectionAdapter connection, Class<T> typeParameterClass){
        this.connection = connection;
        this.typeParameterClass = typeParameterClass;
    }

    public abstract List<T> findAll();

    public abstract T findOne(IExpression where);

    public abstract List<T> find(IExpression where, String[] groupBy, IExpression having);

    public abstract int insert(T object);

    public abstract int update(T object);

    public abstract int delete(T object);

    public abstract int delete(IExpression where);
}
