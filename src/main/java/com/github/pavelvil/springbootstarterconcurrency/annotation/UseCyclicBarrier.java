package com.github.pavelvil.springbootstarterconcurrency.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface UseCyclicBarrier {

    String barrier() default "default";

    int parties();

}
