package com.commerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.NoSuchElementException;

@Controller
public class HomController {

    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping("/exception")
    public void exception() throws Exception {
        throw new NoSuchElementException("그런 거 없어요ㅠㅠ");
    }
}
