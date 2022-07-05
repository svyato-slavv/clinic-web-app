package com.example.clinicwebapp.controller;

import com.example.clinicwebapp.model.dto.ClientDto;
import com.example.clinicwebapp.model.dto.VisitDto;
import com.example.clinicwebapp.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/visits")
@RequiredArgsConstructor
public class VisitsController {

    private final VisitService visitService;

    @GetMapping
    public String index(Model model) {
        List<VisitDto> list = visitService.findAll();
        model.addAttribute("list", list);
        return "visits";
    }

    @GetMapping("/new")
    public String newVisits(VisitDto visitDto) {
        return "edit-visit";
    }

    @PostMapping("/new")
    public String createVisit(@Valid VisitDto dto) {
        visitService.save(dto);
        return "redirect:/visits";
    }

    @GetMapping("/{id}")
    public String visitDetails(@PathVariable("id") Long id, Model model) {
        VisitDto dto = visitService.findById(id).orElseThrow();
        model.addAttribute("visitDto", dto);
        return "visits-details";
    }

    @GetMapping("/{id}/edit")
    public String editVisit(@PathVariable("id") Long id, Model model) {
        VisitDto dto = visitService.findById(id).orElseThrow();
        model.addAttribute("visitDto", dto);
        return "edit-visit";
    }

    @PostMapping("/{id}/edit")
    public String updateVisit(@PathVariable(name = "id") Long id, @Valid VisitDto dto) {
        visitService.update(dto);
        return "redirect:/visits";
    }

    @PostMapping("/{id}/remove")
    public String removeVisit(@PathVariable(name = "id") Long id, @Valid VisitDto dto) {
        visitService.remove(id);
        return "redirect:/visits";
    }

}
