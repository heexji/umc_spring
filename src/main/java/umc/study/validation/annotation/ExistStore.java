// umc.study.validation.annotation.ExistStore.java
package umc.study.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = umc.study.validation.validator.ExistStoreValidator.class)
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistStore {
    String message() default "존재하지 않는 가게입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
