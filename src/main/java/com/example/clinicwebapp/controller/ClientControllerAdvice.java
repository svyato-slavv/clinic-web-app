package com.example.clinicwebapp.controller;

import com.example.clinicwebapp.exc.ClientNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ClientControllerAdvice {
    @ExceptionHandler(ClientNotFoundException.class)
    public String handleClientNotFound(ClientNotFoundException e, Model model){
        model.addAttribute("id",e.getId());
        return "not found";
    }
}
