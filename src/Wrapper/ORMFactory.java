package Wrapper;

import ConnectAdapter.ConnectionAdapter;
import ConnectAdapter.MySQLConnection;

/**
 * Wrapper
 * Create by pmtoan
 * Date 12/31/2022 - 12:54 PM
 * Description: ...
 */
public class ORMFactory {
    public enum DATABASE_TYPE{
        MySQL, MSSQL, MongoDB
    }
    private static DATABASE_TYPE dbType;
    private static String dbHost;
    private static String dbPort;
    private static String dbDatabaseName;
    private static String dbUserName;
    private static String dbPassword;

    public static void setDatabaseInstance(DATABASE_TYPE type){
        dbType = type;
    }

    public static void configDatabase(String host, String port, String databaseName, String userName, String password){
        dbHost = host;
        dbPort = port;
        dbDatabaseName = databaseName;
        dbUserName = userName;
        dbPassword = password;
    }

    public static <T> ORMObject<T> createORMObject(){
        switch (dbType){
            case MySQL:
                ConnectionAdapter connectionAdapter = new MySQLConnection(dbHost, dbPort, dbDatabaseName, dbUserName, dbPassword);
                return new MySqlORM<T>(connectionAdapter);
            case MSSQL:
                return null;
            case MongoDB:
                return null;
            default:
                return null;
        }

    }
}
