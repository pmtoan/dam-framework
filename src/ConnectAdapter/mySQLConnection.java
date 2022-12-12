package ConnectAdapter;


public class mySQLConnection extends ConnectionAdapter
{

    public mySQLConnection(String url,String userName,String password)
    {
        this.url = url;
        this.userName = userName;
        this.password = password;

    }

    public String getDb(){
        return "com.mysql.jdbc.Driver";
    }
}