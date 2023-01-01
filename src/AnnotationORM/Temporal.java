package AnnotationORM;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * PACKAGE_NAME
 * Create by pmtoan
 * Date 12/30/2022 - 12:09 PM
 * Description: ...
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Temporal {
    enum TemporalType{
        DATE, DATETIME
    }
    TemporalType value();
}
