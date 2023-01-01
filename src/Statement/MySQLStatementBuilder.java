package Statement;

import AnnotationORM.AnnotationProcessor;
import Expression.IExpression;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Statement
 * Create by pmtoan
 * Date 12/12/2022 - 3:39 PM
 * Description: ...
 */
public class MySQLStatementBuilder extends StatementBuilder {

    public MySQLStatementBuilder(AnnotationProcessor processor){
         this.processor = processor;
    }

    @Override
    public StatementBuilder selectAll() {
        if(this.selectedColumns != null && !this.selectedColumns.isEmpty()){
            this.selectedColumns.clear();
        }
        this.selectedColumns = null;

        return this;
    }

    @Override
    public StatementBuilder select(String[] cols) {
        this.selectedColumns = new ArrayList<>();
        this.selectedColumns.addAll(List.of(cols));

        return this;
    }

    @Override
    public StatementBuilder groupBy(String column) {
        this.groupByParameter = column;

        return this;
    }

    @Override
    public StatementBuilder having(IExpression expression) {
        this.havingParameter = expression.toString();

        return this;
    }

    @Override
    public String createQueryStatement() {
        String sql = "SELECT " + getCols() + " FROM " + tableName + " ";

        if(whereParameter != null){
            sql += "WHERE " + whereParameter + " ";
        }
        if(groupByParameter != null){
            sql += "GROUP BY " + groupByParameter + " ";
        }
        if(havingParameter != null){
            sql += "HAVING " + havingParameter + " ";
        }

        return sql + ";";
    }

    @Override
    public StatementBuilder insert(Object o) {
        this.updateType = UPDATE_TYPE.INSERT;

        StringBuilder colNames = new StringBuilder("(");
        StringBuilder values = new StringBuilder("VALUES (");

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

        Map<String, Object> res = processor.mapToColumnValue(o);

        for(String colName : res.keySet()){
            if(res.get(colName) != null){
                parameter.append(colName).append("='").append(res.get(colName)).append("', ");
            }
        }

        parameter.deleteCharAt(parameter.length() - 2);

        System.out.println(parameter);

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

        return sql.append(";").toString();
    }

    private String getCols(){
        String queryCols = "";

        if(selectedColumns == null){
            queryCols = "*";
        } else {
            for(String col : this.selectedColumns){
                queryCols += col + ", ";
            }
            queryCols = queryCols.substring(0, queryCols.length()-2);
        }

        return queryCols;
    }
}
