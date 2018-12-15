package util.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<PhoneValidatorConstraint, String> {
	private static final String PHONE_PATTERN = "^[0-9]*$";
	
	public void initialize(PhoneValidatorConstraint paramA) {
		
	}
	
	public boolean isValid(String phone, ConstraintValidatorContext ctx) {
		if (phone == null) {
			return false;
		} else {
			Pattern pattern = Pattern.compile(PHONE_PATTERN);
			Matcher matcher = pattern.matcher(phone);
			return matcher.matches();
		}
	}
}
