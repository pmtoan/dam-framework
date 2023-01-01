package Wrapper;

import AnnotationORM.MySqlAnnotationProcessor;
import ConnectAdapter.ConnectionAdapter;
import Expression.Composite.AndExpression;
import Expression.IExpression;
import Expression.Leaf.Equal;
import Expression.Leaf.GreaterOrEqual;
import Iterator.ResultSetMapper;
import Statement.StatementAdapter;
import Statement.StatementBuilder;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Wrapper
 * Create by pmtoan
 * Date 12/31/2022 - 2:39 PM
 * Description: ...
 */
public class MySqlORM<T> extends ORMObject<T>{
    public MySqlORM(ConnectionAdapter connection) {
        super(connection);
    }

    @Override
    public List<T> find() {
        StatementBuilder statementBuilder = statement.createStatementBuilder();
        String query = statementBuilder.table(tableName)
                //.select(new String[] {"first_name", "last_name"})
                .selectAll()
                //.where(new Equal("first_name", "Cris"))
                //.groupBy("first_name")
                //.having(new GreaterOrEqual("actor_id", 210))
                .createQueryStatement();
        ResultSet rs = statement.executeRawSQLQuery(query);

        return ResultSetMapper.mapResultSetToObject(rs, );
    }

    @Override
    public T findOne(IExpression condition) {


        return null;
    }

    @Override
    public int insert(Object object) {
        connection.connect();
        statement = connection.createStatement();
        StatementBuilder statementBuilder = statement.createStatementBuilder();
        String create = statementBuilder
                .table(tableName)
                .insert(object)
                .createUpdateStatement();


        int rs = statement.executeRawSQLUpdate(create);
        connection.close();
        return rs;
    }

    @Override
    public int update(Object object) {
        connection.connect();
        statement = connection.createStatement();
        StatementBuilder statementBuilder = statement.createStatementBuilder();
        MySqlAnnotationProcessor processor = new MySqlAnnotationProcessor();

        Map.Entry<String, Object> primaryKey = processor.getPKValue(object);

        String update = statementBuilder
                .table(tableName)
                .update(object)
                .where(new Equal(primaryKey.getKey(), (int) primaryKey.getValue()))
                .createUpdateStatement();

        System.out.println(update);

        int rs = statement.executeRawSQLUpdate(update);
        connection.close();
        return rs;
    }

    @Override
    public int delete(Object object) {
        connection.connect();
        statement = connection.createStatement();
        StatementBuilder statementBuilder = statement.createStatementBuilder();
        MySqlAnnotationProcessor processor = new MySqlAnnotationProcessor();

        Map.Entry<String, Object> primaryKey = processor.getPKValue(object);
        String delete = statementBuilder
                .table(tableName)
                .delete()
                .where(new Equal(primaryKey.getKey(), (int) primaryKey.getValue()))
                .createUpdateStatement();

        int rs = statement.executeRawSQLUpdate(delete);
        connection.close();
        return rs;
    }

    @Override
    public int delete(IExpression condition) {
        connection.connect();
        statement = connection.createStatement();
        StatementBuilder statementBuilder = statement.createStatementBuilder();
        String delete = statementBuilder
                .table(tableName)
                .delete()
                .where(condition)
                .createUpdateStatement();

        int rs = statement.executeRawSQLUpdate(delete);
        connection.close();
        return rs;
    }
}
