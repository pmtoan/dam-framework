package Statement;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Statement
 * Create by pmtoan
 * Date 12/11/2022 - 7:56 PM
 * Description: ...
 */
public class MySQLStatement implements StatementAdapter {
    private Statement originalStatement;

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
