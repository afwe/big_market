package cn.bugstack.types.annotations;

import java.lang.annotation.*;

/**
 * @author luke
 * @date 2025年03月30日 15:21
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface DCCValue {
    String value() default "";
}
