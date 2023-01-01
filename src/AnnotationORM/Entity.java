package AnnotationORM;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * AnnotationORM
 * Create by pmtoan
 * Date 1/1/2023 - 12:27 PM
 * Description: ...
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Entity {
}
