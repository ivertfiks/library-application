package com.libraryapp.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
public class HomePageController {

    @RequestMapping("/")
    public String getHomePage(){
        return "home";
    }

    @RequestMapping("/loginPage")
    public String getLoginPage(){
        return "login";
    }


}
