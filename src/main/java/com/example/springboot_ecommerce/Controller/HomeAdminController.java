package com.example.springboot_ecommerce.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeAdminController {

    @RequestMapping("/home")
    public String homepage(){
        return "homepage";
    }
}
