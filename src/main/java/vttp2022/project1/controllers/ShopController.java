package vttp2022.project1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class ShopController {
    
     
    @GetMapping(path="/shop")
    public String Shop(){

        return "shop";
    }
}
