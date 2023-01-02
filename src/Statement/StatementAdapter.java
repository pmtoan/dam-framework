package Statement;

import java.sql.*;

public interface StatementAdapter {
    // Adapter pattern
    public StatementBuilder createStatementBuilder();

    public ResultSet executeRawSQLQuery(String sql);

    public int executeRawSQLUpdate(String sql);
}


