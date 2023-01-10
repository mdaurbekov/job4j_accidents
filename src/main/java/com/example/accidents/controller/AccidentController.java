package com.example.accidents.controller;

import com.example.accidents.model.Accident;
import com.example.accidents.service.AccidentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class AccidentController {
    private final AccidentService accidentService;

    @GetMapping("/formCreate")
    public String formCreate() {
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident) {
        accidentService.create(accident);
        return "redirect:/index";
    }

    @GetMapping("/formUpdate")
    public String formUpdate(Model model, @RequestParam("id") int id) {
        Accident accident = accidentService.findById(id);
        if (accident == null) {
            return "redirect:/error";
        }
        model.addAttribute("accident", accident);
        return "accident/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Accident accident) {
        accidentService.update(accident);
        return "redirect:/index";
    }
}