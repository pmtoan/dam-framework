package Statement;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 * Statement
 * Create by pmtoan
 * Date 12/11/2022 - 7:55 PM
 * Description: ...
 */
public interface StatementAdapter {
    // Adapter pattern

    public StatementBuilder createStatementBuilder();

    public ResultSet executeRawSQLQuery(String sql);

    public int executeRawSQLUpdate(String sql);
}


