package com.rebuy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String hello() {
        return "Re:Buy Backend Server is running!";
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}