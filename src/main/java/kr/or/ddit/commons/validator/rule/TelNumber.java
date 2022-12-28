package kr.or.ddit.commons.validator.rule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @author 서대철
 * @since 2021. 1. 29.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                   수정자               		수정내용
 * --------     --------    ----------------------
 * 2021. 1. 29.        서대철       		    최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=TelNumberValidator.class)
public @interface TelNumber {
	String value() default "\\d{2,3}-\\d{3,4}-\\d{4}";
	String placeholder() default "000-0000-0000";
	
	String message() default "{kr.or.ddit.validate.rule.TelNumber.message}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
