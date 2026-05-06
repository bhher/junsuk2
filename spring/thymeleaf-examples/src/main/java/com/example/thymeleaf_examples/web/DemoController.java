package com.example.thymeleaf_examples.web;

import com.example.thymeleaf_examples.service.DemoItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    private final DemoItemService demoItemService;

    public DemoController(DemoItemService demoItemService) {
        this.demoItemService = demoItemService;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("pageTitle","Thymeleaf 예제홈");
        return "index";
    }


}
