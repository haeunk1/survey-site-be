package com.project.servey.util.custom;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

@Target(ElementType.TYPE)           // Class, Interface, Enum
@Retention(RetentionPolicy.RUNTIME) // Runtime 시에도 유지
@Component                          // Spring Bean으로 등록
public @interface PersistenceAdapter {
    String value() default "";
}
