package com.example.accidents.controller;

import com.example.accidents.model.Accident;
import com.example.accidents.service.AccidentService;
import com.example.accidents.service.AccidentTypeService;
import com.example.accidents.service.RuleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class AccidentController {
    private final AccidentService accidentService;
    private final AccidentTypeService accidentTypeService;
    private final RuleService ruleService;

    @GetMapping("/formCreate")
    public String formCreate(Model model) {
        model.addAttribute("types", accidentTypeService.getAll());
        model.addAttribute("rules", ruleService.getAll());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        accident.setRules(ruleService.getByIds(ids));
        if (!accidentService.create(accident)) {
            return "redirect:/error";
        }
        return "redirect:/index";
    }

    @GetMapping("/formUpdate")
    public String formUpdate(Model model, @RequestParam("id") int id) {
        Optional<Accident> accident = accidentService.findById(id);
        if (accident.isEmpty()) {
            return "redirect:/error";
        }
        model.addAttribute("accident", accident);
        model.addAttribute("types", accidentTypeService.getAll());
        model.addAttribute("rules", ruleService.getAll());
        return "accident/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Accident accident) {
        if (!accidentService.update(accident)) {
            return "redirect:/error";
        }
        return "redirect:/index";
    }
}