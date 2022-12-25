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

    public StatementAdapter table(String tableName);

    public StatementAdapter where(String ex);

    public StatementAdapter selectAll();

    public StatementAdapter select(String[] cols);

    public StatementAdapter groupBy(String ex);

    public StatementAdapter having(String ex);

    public StatementAdapter insert(Object o);

    public StatementAdapter update(Object o);

    public StatementAdapter delete();

    public ResultSet executeQuery();

    public int executeUpdate();

}


