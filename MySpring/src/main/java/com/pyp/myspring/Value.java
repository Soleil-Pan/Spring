package com.pyp.myspring;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)    /*成员变量*/
@Retention(RetentionPolicy.RUNTIME)    /*运行时*/
public @interface Value {
    //不能为空
    String value();
}
