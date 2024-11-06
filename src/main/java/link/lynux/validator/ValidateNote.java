package link.lynux.validator;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(value = ValidateNotes.class)
public @interface ValidateNote {
    ValidateRule rule() default ValidateRule.NONE;

    boolean isMandatory() default false;

    String express() default "";
}