package com.bwtfs.server.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // 作用位置，作用于方法上
//@Target(ElementType.TYPE) // 作用位置，作用于类上
@Retention(RetentionPolicy.RUNTIME) // 注解保留时间，运行时保留
public @interface WebDev {
}
