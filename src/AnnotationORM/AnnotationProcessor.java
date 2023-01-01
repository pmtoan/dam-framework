package AnnotationORM;

import java.util.Map;

/**
 * AnnotationORM
 * Create by pmtoan
 * Date 12/31/2022 - 2:34 PM
 * Description: ...
 */
public interface AnnotationProcessor {
    public String toTableName(Object object);

    public Map.Entry<String, Object> getPKValue(Object object);

    public boolean isPrimaryKey(Object object, String colName);

    public Map<String, Object> mapToColumnValue(Object object);
}

// Class -> Row
// id: 1, firstName: Cris, lastName: Devil, lastUpdate: 2022-11-24 12:20:27.0
// actor_id: 1, first_name: Cris, last_name: Devil, last_update: 2022-11-24 12:20:27.0
