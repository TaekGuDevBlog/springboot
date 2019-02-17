package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String testMapping() {
        return "test";
    }

    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public String test2Mapping() {
        return "test";
    }

    @RequestMapping(value = "/test3", method = RequestMethod.GET)
    public String test3Mapping() {
        return "test";
    }
}
