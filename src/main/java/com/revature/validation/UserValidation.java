package com.revature.validation;

import com.revature.exception.ValidatorException;
import com.revature.model.UserDetails;

public class UserValidation {
public static void validateBeforeRegistration(UserDetails user) throws ValidatorException {
		
		String name = user.getCustomername();
		if ( name == null || "".equals(name.trim())) {
			throw new ValidatorException("Invalid Name");
		}
	}
}
