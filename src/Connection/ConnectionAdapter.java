package Connection;
import Statement.StatementAdapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class ConnectionAdapter {
    // Adapter

    protected Connection connection ;
    protected String host;
    protected String port;
    protected String databaseName;
    protected String userName;
    protected String password;

    protected abstract String getDb();

    protected abstract String getDbUrl();

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

    public boolean isClose(){
        if (this.connection!=null){
            try {
                return connection.isClosed();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public boolean isConnect(){
        if (this.connection!=null){
            try {
                return connection.isValid(10);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
