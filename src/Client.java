import java.lang.reflect.Field;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import AnnotationORM.Column;
import AnnotationORM.Table;
import ConnectAdapter.ConnectionAdapter;
import ConnectAdapter.MySQLConnection;
import Expression.Composite.AndExpression;
import Expression.Leaf.*;
import Statement.StatementAdapter;
import Statement.StatementBuilder;
import Iterator.*;
import Wrapper.*;

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

        Actor actor = new Actor("Chris", "Hemsworth");
        actor.setId(250);
        actor.setLastUpdate(new Date("2019/2/19"));
        System.out.println(actor.getLastUpdate());


        ORMFactory.setDatabaseInstance(ORMFactory.DATABASE_TYPE.MySQL);
        ORMFactory.configDatabase(host, port, databaseName, userName, password);

        ORMObject<Actor> actorORMObject = ORMFactory.createORMObject();
        int rowAffected = 0;

        // Find
//        Actor rsActor = actorORMObject.findOne(new Equal(actor.firstName, "Cris"));
//        List<Actor> listActor = actorORMObject.find();

//        if (actorORMObject != null) {
            rowAffected = actorORMObject.insert(actor);
//
//            rowAffected = actorORMObject.update(actor);
//
//            rowAffected = actorORMObject.delete(actor);
//
//            rowAffected = actorORMObject.delete(new Equal( "actor_id", "194"));
//        }

        //-----------------------------

        ConnectionAdapter connection = new MySQLConnection(host, port, databaseName, userName, password);
        //System.out.println(connection.getDbUrl());

        connection.connect();

        StatementAdapter myStatement = connection.createStatement();

        StatementBuilder statementBuilder = myStatement.createStatementBuilder();

        String query = statementBuilder.table("actor")
                //.select(new String[] {"first_name", "last_name"})
                .selectAll()
                //.where(new Equal("first_name", "Cris"))
                //.groupBy("first_name")
                .having(new GreaterOrEqual("actor_id", 170))
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

        //System.out.println(query);
        ResultSet rs = myStatement.executeRawSQLQuery(query);


        List<Actor> list = ResultSetMapper.mapResultSetToObject(rs, Actor.class);

        for(Actor a : list){
            System.out.print(a.id + " - ");
            System.out.print(a.firstName + " - ");
            System.out.print(a.lastName + " - ");
            System.out.print(a.lastUpdate);
            System.out.println();
        }

        connection.close();
    }
}
