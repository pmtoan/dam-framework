package Iterator;

import AnnotationORM.Column;
import AnnotationORM.Entity;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Iterator
 * Create by pmtoan
 * Date 1/1/2023 - 12:28 PM
 * Description: ...
 */
public class ResultSetMapper<T> {

    public static <T> List<T> mapResultSetToObject(ResultSet rs, Class outputClass){
        List<T> outputList = null;

        try{
            if(rs != null){
                if(outputClass.isAnnotationPresent(Entity.class)){
                    ResultSetMetaData resultSetMetaData = rs.getMetaData();

                    Field[] fields = outputClass.getFields();

                    while(rs.next()){
                        T bean = (T) outputClass.newInstance();
                        for(int i = 0; i < resultSetMetaData.getColumnCount(); i++){
                            String colName = resultSetMetaData.getColumnName(i+1);

                            Object colValue = rs.getObject(i+1);

                            for(Field field : fields){
                                if(field.isAnnotationPresent(Column.class)){
                                    Column columnAnnotation = field.getDeclaredAnnotation(Column.class);
                                    if(columnAnnotation.name().equalsIgnoreCase(colName) && colValue != null){
                                        field.set(bean, colValue);
                                    }
                                }
                            }
                        }

                        if(outputList == null){
                            outputList = new ArrayList<>();
                        }
                        outputList.add(bean);
                    }
                }
            } else {
                return null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return outputList;
    }
}
