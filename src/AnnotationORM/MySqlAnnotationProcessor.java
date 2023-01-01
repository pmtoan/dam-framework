package AnnotationORM;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * AnnotationORM
 * Create by pmtoan
 * Date 12/31/2022 - 3:30 PM
 * Description: ...
 */
public class MySqlAnnotationProcessor implements AnnotationProcessor{

    @Override
    public String toTableName(Object object) {
        Class<?> clazz = object.getClass();
        Table tableAnnotation = clazz.getDeclaredAnnotation(Table.class);

        return tableAnnotation.name();
    }

    @Override
    public Map.Entry<String, Object> getPKValue(Object object) {
        Map.Entry<String, Object> rs = null;
        for(Field field : getFields(object)){
            Id annotationId = field.getDeclaredAnnotation(Id.class);
            if(annotationId != null){
                Column annotationColumn = field.getDeclaredAnnotation(Column.class);
                try {
                    rs = new AbstractMap.SimpleEntry<>(annotationColumn.name(), field.get(object));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return rs;
    }

    @Override
    public boolean isPrimaryKey(Object object, String colName) {
        for(Field field : getFields(object)){
            Id annotationId = field.getDeclaredAnnotation(Id.class);
            if(annotationId != null){
                Column annotationColumn = field.getDeclaredAnnotation(Column.class);
                return annotationColumn.name().equals(colName);
            }
        }

        return false;
    }

    @Override
    public Map<String, Object> mapToColumnValue(Object object) {
        Map<String, Object> resMap = new LinkedHashMap<>();

        try {
            for(Field field : getFields(object)){
                Column colAnnotation = field.getDeclaredAnnotation(Column.class);
                Transient transientAnnotation = field.getDeclaredAnnotation(Transient.class);
                Temporal temporalAnnotation = field.getDeclaredAnnotation(Temporal.class);

                if(transientAnnotation != null){
                    continue;
                }

                if(field.get(object) == null){
                    if(colAnnotation.nullable()){
                        continue;
                    } else {
                        throw new IllegalAccessException();
                    }
                }

                String col = Optional.ofNullable(colAnnotation).map(Column::name).orElse(field.getName());
                resMap.put(col, field.get(object));

                if(temporalAnnotation != null){
                    String datetime =  new Date().toString();
                    if(temporalAnnotation.value() == Temporal.TemporalType.DATE){
                        datetime = new SimpleDateFormat("yyyy-MM-dd").format(field.get(object));
                    }
                    else if(temporalAnnotation.value() == Temporal.TemporalType.DATETIME){
                        datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(field.get(object));
                    }

                    resMap.replace(col, datetime);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return resMap;
    }

    private Field[] getFields(Object object){
        Class<?> clazz = object.getClass();
        return clazz.getFields();
    }
}
