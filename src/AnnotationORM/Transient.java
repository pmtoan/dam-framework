package AnnotationORM;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * AnnotationORM
 * Create by pmtoan
 * Date 12/30/2022 - 3:17 PM
 * Description: ...
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Transient {
}
