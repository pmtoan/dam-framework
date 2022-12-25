package ConnectAdapter;
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
    public Connection connection ;
    protected String url;
    protected String userName;
    protected String password;


    public abstract String getDb();
    public void connect(){

        try{
            Class.forName(getDb());
            this.connection = DriverManager.getConnection(this.url,this.userName,this.password);
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
