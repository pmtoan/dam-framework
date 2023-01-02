package Statement;

import AnnotationORM.MySqlAnnotationProcessor;
import Expression.IExpression;
import java.util.Map;

public class MySQLStatementBuilder extends StatementBuilder {

    public MySQLStatementBuilder(){

    }

    @Override
    public StatementBuilder selectAll() {
        this.selectedColumns = "*";

        return this;
    }

    @Override
    public StatementBuilder select(String[] cols) {
        this.selectedColumns = null;
        if(cols != null){
            this.selectedColumns = getCols(cols);
        }

        return this;
    }

    @Override
    public StatementBuilder groupBy(String[] cols) {
        this.groupByParameter = null;
        if(cols != null){
            this.groupByParameter = getCols(cols);
        }

        return this;
    }

    @Override
    public StatementBuilder having(IExpression expression) {
        this.havingParameter = null;
        if(expression != null){
            this.havingParameter = expression.toString();
        }

        return this;
    }

    @Override
    public String createQueryStatement() {
        String sql = "SELECT " + selectedColumns + " FROM " + tableName + " ";

        if(whereParameter != null){
            sql += "WHERE " + whereParameter + " ";
        }
        if(groupByParameter != null){
            sql += "GROUP BY " + groupByParameter + " ";
        }
        if(havingParameter != null){
            sql += "HAVING " + havingParameter + " ";
        }

        reset();
        return sql + ";";
    }

    @Override
    public StatementBuilder insert(Object o) {
        this.updateType = UPDATE_TYPE.INSERT;

        StringBuilder colNames = new StringBuilder("(");
        StringBuilder values = new StringBuilder("VALUES (");

        MySqlAnnotationProcessor processor = new MySqlAnnotationProcessor();
        Map<String, Object> res = processor.mapToColumnValue(o);

        for(String colName : res.keySet()){
            if(res.get(colName) != null && !processor.isPrimaryKey(o, colName)){
                colNames.append(colName).append(", ");
                values.append("'").append(res.get(colName)).append("', ");
            }
        }

        colNames.setCharAt(colNames.length()-2, ')');
        values.setCharAt( values.length()-2,')');

        this.objectValueParameter = (colNames.toString() + values.toString()).trim();

        return this;
    }

    @Override
    public StatementBuilder update(Object o) {
        this.updateType = UPDATE_TYPE.UPDATE;

        StringBuilder parameter = new StringBuilder("SET ");

        MySqlAnnotationProcessor processor = new MySqlAnnotationProcessor();
        Map<String, Object> res = processor.mapToColumnValue(o);

        for(String colName : res.keySet()){
            if(res.get(colName) != null){
                parameter.append(colName).append("='").append(res.get(colName)).append("', ");
            }
        }

        parameter.deleteCharAt(parameter.length() - 2);

        this.objectValueParameter = parameter.toString().trim();

        return this;
    }

    @Override
    public StatementBuilder delete() {
        this.updateType = UPDATE_TYPE.DELETE;
        this.objectValueParameter = null;

        return this;
    }

    @Override
    public String createUpdateStatement() {
        StringBuilder sql = new StringBuilder("");
        String middleStatement = this.tableName + " " + this.objectValueParameter + " ";

        switch (this.updateType){
            case INSERT:
                sql.append("INSERT INTO ")
                        .append(middleStatement.trim());

                break;
            case UPDATE:
                sql.append("UPDATE ")
                        .append(middleStatement);

                if(this.whereParameter != null){
                    sql.append("WHERE ").append(this.whereParameter);
                }

                break;
            case DELETE:
                sql.append("DELETE FROM ").append(this.tableName).append(" ")
                        .append("WHERE ").append(this.whereParameter);

                break;
        }

        reset();
        return sql.append(";").toString();
    }

    private String getCols(String[] cols){
        StringBuilder queryCols = new StringBuilder();

        for (String col : cols) {
            queryCols.append(col).append(", ");
        }
        queryCols.delete(queryCols.length()-2,queryCols.length()-1);

        return queryCols.toString();
    }
}
