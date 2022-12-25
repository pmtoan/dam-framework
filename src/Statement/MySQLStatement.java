package Statement;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Statement
 * Create by pmtoan
 * Date 12/11/2022 - 7:56 PM
 * Description: ...
 */
public class MySQLStatement implements StatementAdapter {
    private String tableName;

    private List<String> columns;
    private String allColumn;

    private String groupByParameter = null;
    private String havingParameter = null;
    private String whereParameter= null;

    private String updateStatement;
    private String queryStatement;

    private Statement originalStatement;

    public MySQLStatement(Statement statement){
        this.originalStatement = statement;
    }

    public void reset(){
        tableName = null;

        if(this.columns != null){
            this.columns.clear();
            this.columns = null;
        }
        allColumn = null;

        groupByParameter = null;
        havingParameter = null;
        whereParameter= null;

        updateStatement = null;
        queryStatement = null;
    }


    @Override
    public StatementAdapter selectAll() {
        this.allColumn = "*";
        if(this.columns != null){
            this.columns.clear();
            this.columns = null;
        }

        return this;
    }

    @Override
    public StatementAdapter select(String[] cols) {
        this.columns = new ArrayList<>();
        this.columns.addAll(List.of(cols));
        this.allColumn = null;

        return this;
    }

    @Override
    public StatementAdapter groupBy(String parameter) {
        this.groupByParameter = "GROUP BY " + parameter + " ";

        return this;
    }

    @Override
    public StatementAdapter having(String parameter) {
        this.havingParameter = "HAVING " + parameter + " ";

        return this;
    }

    @Override
    public StatementAdapter insert(Object o) {
        String statement = "INSERT INTO " + tableName + " ";
        StringBuilder colName = new StringBuilder("(");
        StringBuilder values = new StringBuilder("VALUES (");

        for(Field attribute : o.getClass().getFields()){
            try {
                colName.append(attribute.getName()).append(", ");

                values.append("'").append(attribute.get(o)).append("', ");

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        colName.setCharAt(colName.length()-2, ')');
        values.setCharAt( values.length()-2,')');

        updateStatement = statement + colName + values;

        this.whereParameter = null;

        return this;
    }

    @Override
    public StatementAdapter update(Object o) {
        String statement = "UPDATE " + tableName + " ";
        String parameter = "SET ";

        for(Field attribute : o.getClass().getFields()){
            try {
                parameter += attribute.getName() + "='" + attribute.get(o) + "', ";

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        parameter = parameter.substring(0, parameter.length()-2);

        updateStatement = statement + parameter;

        return this;
    }

    @Override
    public StatementAdapter delete() {
        updateStatement = "DELETE FROM " + tableName + " ";

        return this;
    }

    @Override
    public StatementAdapter table(String tableName) {
        this.tableName = tableName;

        return this;
    }

    @Override
    public StatementAdapter where(String parameter) {
        this.whereParameter = "WHERE " + parameter + " ";

        return this;
    }

    @Override
    public ResultSet executeQuery() {
        ResultSet rs = null;

        String sql = "SELECT " + getCols() + " FROM " + tableName + " ";

        if(whereParameter != null){
            sql += whereParameter;
        }
        if(groupByParameter != null){
            sql += groupByParameter;
        }
        if(havingParameter != null){
            sql += havingParameter;
        }

        System.out.println(sql);

        try {
            rs = originalStatement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        reset();

        return rs;
    }

    @Override
    public int executeUpdate() {
        int rs = 0;

        if(whereParameter != null){
            updateStatement += whereParameter;
        }

        System.out.println(updateStatement);

        try {
            rs = originalStatement.executeUpdate(updateStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        reset();

        return rs;
    }

    private String getCols(){
        String queryCols = "";

        if(this.columns == null){
            queryCols = this.allColumn;
        }
        else {
            for(String col : columns){
                queryCols += col + ", ";
            }
            queryCols = queryCols.substring(0, queryCols.length()-2);
        }

        return queryCols;
    }
}
