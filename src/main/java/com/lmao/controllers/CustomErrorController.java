package com.lmao.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;


@Controller
public class CustomErrorController extends AbstractErrorController {
    private static final String ERROR_PATH = "/error";


    @Autowired
    public CustomErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public String notFound() {
        return "errors/page-not-found";
    }



    @RequestMapping(ERROR_PATH)
    public ResponseEntity<?> handleErrors(HttpServletRequest request) throws ChangeSetPersister.NotFoundException {
        HttpStatus status = getStatus(request);

        if (status.equals(HttpStatus.NOT_FOUND)) {
            throw new ChangeSetPersister.NotFoundException();
        }
        return ResponseEntity.status(status).body(getErrorAttributes(request, false));
    }


    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
