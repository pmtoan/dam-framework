package Statement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLStatement implements StatementAdapter {
    private final Statement originalStatement;

    public MySQLStatement(Statement statement){
        this.originalStatement = statement;
    }

    @Override
    public StatementBuilder createStatementBuilder() {
        return new MySQLStatementBuilder();
    }

    @Override
    public ResultSet executeRawSQLQuery(String sql) {
        ResultSet rs = null;

        try {
            rs = originalStatement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    @Override
    public int executeRawSQLUpdate(String sql) {
        int rs = 0;

        try {
            rs = originalStatement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }
}
