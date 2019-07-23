package com.lmao.controllers;

import com.lmao.areas.images.customValidations.multipartFile.MultipartFileIsImage;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
public class MultipartFileInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod method = (HandlerMethod) handler;

        boolean result = true;

        if (method.hasMethodAnnotation(MultipartFileIsImage.class)) {
            MultipartFileIsImage annotation = method.getMethodAnnotation(MultipartFileIsImage.class);

            Part part = request.getPart("file");
            String fileName = part.getSubmittedFileName().toString();

            String[] extensions = annotation.allowedExtensions();
            result = false;
            for (int i = 0; i < extensions.length; i++) {
                if (fileName.endsWith(extensions[i])) {
                    result = true;
                    break;
                }
            }
        }

        if(!result) {
            response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "File extension in not allowed.");
        }

        return result;
    }
}
