package Connection;


import Statement.MySQLStatement;
import Statement.StatementAdapter;

import java.sql.SQLException;

public class MySQLConnection extends ConnectionAdapter {

    public MySQLConnection(String host, String port, String databaseName, String userName, String password) {
        this.host = host;
        this.port = port;
        this.databaseName = databaseName;
        this.userName = userName;
        this.password = password;
    }

    protected String getDb(){
        return "com.mysql.cj.jdbc.Driver";
    }

    @Override
    protected String getDbUrl() {
        return String.format("jdbc:mysql://%s:%s/%s", this.host,this.port,this.databaseName);
    }

    @Override
    public StatementAdapter createStatement() {
        try {
            return new MySQLStatement(connection.createStatement());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}