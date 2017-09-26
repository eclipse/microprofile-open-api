package org.eclipse.microprofile.openapi.annotations.parameters;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.eclipse.microprofile.openapi.annotations.Parameter;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.PARAMETER,
        ElementType.METHOD })
@Inherited
public @interface Parameters {
    public Parameter[] parameters() default @Parameter;

    public Parameter[] value() default {@Parameter};
}
