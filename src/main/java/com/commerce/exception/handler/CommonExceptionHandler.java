package com.commerce.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice(basePackages = {"com.commerce.controller"})
public class CommonExceptionHandler {

    @ExceptionHandler
    public String commonExceptionHandler(Exception e, Model model) {
        model.addAttribute("exception", e.getClass());
        model.addAttribute("message", e.getMessage());

        return "errors/error";
    }
}
