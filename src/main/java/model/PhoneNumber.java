package model;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.validation.constraints.Size;


@Component
public class PhoneNumber implements Validator {
    @Size(max = 10, min = 2)
    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return PhoneNumber.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        PhoneNumber phoneNumber = (PhoneNumber) o;
        String number = phoneNumber.getNumber();
        ValidationUtils.rejectIfEmpty(errors,"number","number.empty");
        if (number.length()<10 || number.length() >11){
            errors.reject("numbers","number.length");
        }
        if (!number.startsWith("0")){
            errors.reject("numbers","number.startsWith");
        }
        if (!number.matches("(^$[0-9]*$)")){
            errors.reject("numbers","number.matches");
        }
    }
}
