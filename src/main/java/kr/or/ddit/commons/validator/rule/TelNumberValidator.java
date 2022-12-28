package kr.or.ddit.commons.validator.rule;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

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
public class TelNumberValidator implements ConstraintValidator<TelNumber, String>{
	private TelNumber telNumber;
	
	@Override
	public void initialize(TelNumber constraintAnnotation) {
		this.telNumber = constraintAnnotation;
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		boolean valid = true;
		if(value != null && !value.isEmpty()) {
			valid = value.matches(telNumber.value());
		}
		return valid;
	}
}
