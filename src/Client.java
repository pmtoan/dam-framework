import java.sql.*;
import java.util.*;
import java.util.Date;

import ConnectAdapter.ConnectionAdapter;
import ConnectAdapter.MySQLConnection;
import Expression.Composite.AndExpression;
import Expression.Composite.OrExpression;
import Expression.IExpression;
import Expression.Leaf.*;
import Statement.StatementAdapter;
import Statement.StatementBuilder;
import Statement.MySQLStatementBuilder;
import Statement.MySQLStatement;

/**
 * PACKAGE_NAME
 * Create by pmtoan
 * Date 12/11/2022 - 7:46 PM
 * Description: ...
 */
public class Client {
    public static void main(String args[]){
        // Oracle:      jdbc:oracle:thin:[<user>/<password>]@<host>[:<port>]:<SID>
        // MySQL:       jdbc:mysql://mysql.db.server:3306/my_database?useSSL=false&serverTimezone=UTC
        // SQLServer:   jdbc:sqlserver://mssql.db.server\\mssql_instance;databaseName=my_database
        // PostgreSQL:  jdbc:postgresql://host:port/database?properties

        String dbURL = "jdbc:mysql://localhost:3306/sakila";

        String host = "localhost";
        String port = "3306";
        String databaseName = "sakila";
        String userName = "root";
        String password = "";

        Actor actor = new Actor("Cris", "Devil Gamer");

        ConnectionAdapter connection = new MySQLConnection(host, port, databaseName, userName, password);
        System.out.println(connection.getDbUrl());
        connection.connect();

        StatementAdapter myStatement = connection.createStatement();

        StatementBuilder statementBuilder = myStatement.createStatementBuilder();

        String query = statementBuilder.table("actor")
                //.select(new String[] {"first_name", "last_name"})
                .selectAll()
                //.where(new Equal("first_name", "Cris"))
                //.groupBy("first_name")
                .having(new GreaterOrEqual("actor_id", 210))
                .createQueryStatement();

        String create = statementBuilder
                .table("actor")
                .insert(actor)
                .createUpdateStatement();

        String update = statementBuilder
                .table("actor")
                .update(actor)
                .where(new Equal("actor_id", 239))
                .createUpdateStatement();

        String delete = statementBuilder
                .table("actor")
                .delete()
                .where(new AndExpression(new Equal("first_name", "cris"),
                        new Equal("last_name", "devil gamer")))
                .createUpdateStatement();

        //System.out.println("Row affected: " + myStatement.executeRawSQLUpdate(delete));

        System.out.println(query);
        ResultSet rs = myStatement.executeRawSQLQuery(query);

        List<Map<String, Object>> result = new ArrayList<>();


        try{
            while (rs.next()) {
                Map<String, Object> resMap = new LinkedHashMap<>();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    resMap.put(rs.getMetaData().getColumnName(i), rs.getObject(i));
                }
                result.add(resMap);
            }

            for(String key : result.get(0).keySet()){
                System.out.print(key + " | ");
            }
            System.out.println();
            for(Map<String, Object> item : result){
                for(String key : item.keySet()){
                    System.out.print(item.get(key) + " | ");
                }
                System.out.println();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        connection.close();
    }
}
