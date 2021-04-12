package com.pyp.myspring;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)   /*类型*/
@Retention(RetentionPolicy.RUNTIME)    /*运行时*/
public @interface Component {
    //传值，可以为空
    String value() default "";
}
