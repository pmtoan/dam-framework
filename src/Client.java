import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Statement.StatementAdapter;
import Statement.MySQLStatement;

/**
 * PACKAGE_NAME
 * Create by pmtoan
 * Date 12/11/2022 - 7:46 PM
 * Description: ...
 */
public class Client {
    public static void main(String args[]){
        String dbURL = "jdbc:mysql://localhost:3306/sakila";
        String userName = "root";
        String password = "";

        Actor c = new Actor("Chris", "Devil Gamer");

        for(Field a :  c.getClass().getFields()){
            try {
                System.out.println(a.getName() + ": " + a.get(c));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }


        Connection conn = null;
        try {
            //approach 1
            //Class.forName("com.mysql.cj.jdbc.Driver");

            //approach 2
            Driver myDriver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(myDriver);

            // Create connection
            conn = DriverManager.getConnection(dbURL, userName, password);
            System.out.println("connect successfully!");

            StatementAdapter stmt = new MySQLStatement(conn.createStatement());




            //stmt.table("actor").insert(c).executeUpdate();

            //stmt.table("actor").update(c).where("actor_id='235'").executeUpdate();

            stmt.table("actor").delete().where("first_name='chris' and last_name='hemsworth'").executeUpdate();

            ResultSet rs = stmt.table("actor").selectAll().where("first_name='chris'").executeQuery();
            //System.out.println(s.executeUpdate());

            //int rs1 = stmt.table("actor").insert(new QueryStatement(conn.createStatement())).executeUpdate();


//            ResultSet rs = stmt.table("actor")
//                    .select(new String[] {"first_name", "last_name"}).selectAll()
//                    //.where("first_name = 'Chris'")
//                    .executeQuery();
            List<Map<String, Object>> result = new ArrayList<>();
            while (rs.next()) {
                Map<String, Object> resMap = new HashMap<>();
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
            // close connection
            conn.close();
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
    }
}
