package Mapper;

import AnnotationORM.Column;
import AnnotationORM.Entity;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetMapper<T> {

    public static <T> List<T> mapResultSetToListObject(ResultSet rs, Class<T> outputClass){
        List<T> outputList = new ArrayList<>();

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

    public static <T> T mapResultSetToObject(ResultSet rs, Class<T> outputClass){
        T outputObject = null;

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

                        outputObject = bean;
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
        return outputObject;
    }
}
