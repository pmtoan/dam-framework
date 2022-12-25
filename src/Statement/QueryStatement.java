package Statement;

import java.sql.Statement;
import java.util.List;

/**
 * Statement
 * Create by pmtoan
 * Date 12/12/2022 - 3:39 PM
 * Description: ...
 */
public class QueryStatement extends AbstractStatement {
    private final Statement originalStatement;
    private String tableName;
    private List<String> columns;

    public QueryStatement(Statement originalStatement) {
        this.originalStatement = originalStatement;
    }
}
