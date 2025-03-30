package cn.bugstack.types.annotations;

import java.lang.annotation.*;

/**
 * @author luke
 * @date 2025年03月30日 16:38
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface RateLimiterAccessInterceptor {
    String key() default "all";

    double permitsPerSecond();

    double blacklistCount() default 0;

    String fallbackMethod();

}
