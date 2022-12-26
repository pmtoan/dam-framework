package ConnectAdapter;
import Statement.MySQLStatement;
import Statement.StatementAdapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * PACKAGE_NAME
 * Create by pmtoan
 * Date 12/5/2022 - 3:25 PM
 * Description: ...
 */
public abstract class ConnectionAdapter {
    // Adapter

    public Connection connection ;
    protected String host;
    protected String port;
    protected String databaseName;
    protected String userName;
    protected String password;

    public abstract String getDb();

    public abstract String getDbUrl();

    public abstract StatementAdapter createStatement();

    // Template Method
    public void connect(){

        try{
            Class.forName(getDb());
            this.connection = DriverManager.getConnection(getDbUrl(),this.userName,this.password);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            System.out.println(getDb());
            e.printStackTrace();
        }
    }

    public void close(){
        if (this.connection!=null){
            try{
                this.connection.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }


}
