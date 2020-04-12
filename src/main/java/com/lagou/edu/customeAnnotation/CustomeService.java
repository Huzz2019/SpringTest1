package com.lagou.edu.customeAnnotation;

import java.lang.annotation.*;

/**
 * @author huzz
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface CustomeService {
    String value() default "";

}
