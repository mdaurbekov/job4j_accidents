package com.example.accidents.controller;

import com.example.accidents.service.AccidentService;
import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@ThreadSafe
@AllArgsConstructor
public class IndexController {
    private AccidentService accidentService;

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("user", "Petr Arsentev");
        model.addAttribute("accidents", accidentService.getAll());
        return "index";
    }
}
