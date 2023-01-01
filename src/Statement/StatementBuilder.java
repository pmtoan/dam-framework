package Statement;

import AnnotationORM.AnnotationProcessor;
import AnnotationORM.MySqlAnnotationProcessor;
import Expression.IExpression;

import java.util.List;

/**
 * Statement
 * Create by pmtoan
 * Date 12/12/2022 - 4:58 PM
 * Description: ...
 */
public abstract class StatementBuilder {
    // Builder

    public enum UPDATE_TYPE{
        INSERT, UPDATE, DELETE
    }

    protected UPDATE_TYPE updateType;
    protected String tableName;

    protected List<String> selectedColumns;

    protected String objectValueParameter;
    protected String groupByParameter;
    protected String havingParameter;
    protected String whereParameter;

    protected String updateStatement;
    protected String queryStatement;

    AnnotationProcessor processor;

    public void reset(){
        tableName = null;

        groupByParameter = null;
        havingParameter = null;
        whereParameter= null;

        updateStatement = null;
        queryStatement = null;
    }
    
    public StatementBuilder table(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public StatementBuilder where(IExpression expression) {
        this.whereParameter = expression.toString();
        
        return this;
    }

    public StatementBuilder selectAll() {
        return this;
    }

    public StatementBuilder select(String[] cols) {
        return this;
    }

    public StatementBuilder groupBy(String column) {
        return this;
    }

    public StatementBuilder having(IExpression expression) {
        return this;
    }

    public StatementBuilder insert(Object o) {
        return this;
    }

    public StatementBuilder update(Object o) {
        return this;
    }

    public StatementBuilder delete() {
        return this;
    }

    public String createQueryStatement() {
        return null;
    }

    public String createUpdateStatement() {
        return null;
    }
}
