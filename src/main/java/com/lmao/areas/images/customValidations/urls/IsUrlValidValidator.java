package com.lmao.areas.images.customValidations.urls;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class IsUrlValidValidator implements ConstraintValidator<IsUrlValid, String> {
    @Override
    public void initialize(IsUrlValid isUrlValid) {

    }

    @Override
    public boolean isValid(String urlAsString, ConstraintValidatorContext constraintValidatorContext) {
        Boolean result = false;
        try{
            URL url = new URL(urlAsString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            result = (connection.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (ProtocolException e) {
            result = false;
        } catch (MalformedURLException e) {
            result = false;
        } catch (IOException e) {
            result = false;
        }

        return result;
    }
}
