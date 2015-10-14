package com.mb.spring.alias;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * This annotation is used in order to specify all supported alias names for a spring component.
 *
 * @author Michael Bolgar
 * @version Jan 5, 2014
 */
@Target(TYPE)
@Retention(RUNTIME)
@Documented
public @interface Alias {

    String[] value();

}
