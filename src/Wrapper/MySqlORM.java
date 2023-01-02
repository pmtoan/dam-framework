package Wrapper;

import AnnotationORM.MySqlAnnotationProcessor;
import Connection.ConnectionAdapter;
import Expression.IExpression;
import Expression.Leaf.Equal;
import Mapper.ResultSetMapper;
import Statement.StatementBuilder;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class MySqlORM<T> extends ORMObject<T>{
    public MySqlORM(ConnectionAdapter connection, Class<T> typeParameterClass) {
        super(connection, typeParameterClass);
    }

    @Override
    public List<T> findAll() {
        connection.connect();
        statement = connection.createStatement();
        StatementBuilder statementBuilder = statement.createStatementBuilder();
        String query = statementBuilder.table(tableName)
                .selectAll()
                .createQueryStatement();
        ResultSet rs = statement.executeRawSQLQuery(query);
        List<T> resultList = ResultSetMapper.mapResultSetToListObject(rs, typeParameterClass);

        connection.close();
        return resultList;
    }

    @Override
    public List<T> find(IExpression where, String[] groupBy, IExpression having) {
        connection.connect();
        statement = connection.createStatement();
        StatementBuilder statementBuilder = statement.createStatementBuilder();
        String query = statementBuilder.table(tableName)
                .selectAll()
                .where(where)
                .groupBy(groupBy)
                .having(having)
                .createQueryStatement();
        ResultSet rs = statement.executeRawSQLQuery(query);
        List<T> resultList = ResultSetMapper.mapResultSetToListObject(rs, typeParameterClass);

        connection.close();
        return resultList;
    }

    @Override
    public T findOne(IExpression where) {
        connection.connect();
        statement = connection.createStatement();
        StatementBuilder statementBuilder = statement.createStatementBuilder();
        String query = statementBuilder.table(tableName)
                .selectAll()
                .where(where)
                .createQueryStatement();
        ResultSet rs = statement.executeRawSQLQuery(query);
        T resultObject = ResultSetMapper.mapResultSetToObject(rs, typeParameterClass);

        connection.close();
        return resultObject;
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
