package AnnotationORM;

import java.util.Map;

public interface AnnotationProcessor {
    public String toTableName(Object object);

    public Map.Entry<String, Object> getPKValue(Object object);

    public boolean isPrimaryKey(Object object, String colName);

    public Map<String, Object> mapToColumnValue(Object object);
}