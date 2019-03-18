package com.practice.springboot.basics.SpringBootRESTApp.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AuthorNameValidator implements ConstraintValidator<AuthorName, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		if (value != null && value.equals("Pradeep")) {
			return true;
		} else {
			return false;
		}
	}

}
