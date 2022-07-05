package com.example.clinicwebapp.controller;

import com.example.clinicwebapp.exc.DoctorNotFoundException;
import com.example.clinicwebapp.model.dto.DoctorDto;
import com.example.clinicwebapp.service.DoctorService;
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
@RequiredArgsConstructor
@RequestMapping("/doctors")
public class DoctorsController {

    private final DoctorService doctorService;

    @GetMapping
    private String index(Model model) {
        List<DoctorDto> list = doctorService.findAll();
        model.addAttribute("list", list);
        return "doctors";
    }

    @GetMapping("/new")
    private String newDoctor(DoctorDto doctorDto) {
        return "edit-doctors";
    }

    @PostMapping("/new")
    public String createDoctor(@Valid DoctorDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-doctors";
        }
        doctorService.save(dto);
        return "doctor-created";
    }

    @GetMapping("/{id}")
    public String doctorInfo(@PathVariable("id") Long id, Model model) {
        if (!doctorService.existById(id)) {
            return "redirect:/doctors";
        }
        DoctorDto dto = doctorService.findById(id).orElseThrow(() -> new DoctorNotFoundException(id));
        model.addAttribute("doctorDto", dto);
        return "doctors-details";
    }

    @GetMapping("/{id}/edit")
    public String editDoctor(@PathVariable(name = "id")Long id,Model model){
        if (!doctorService.existById(id)) {
            return "redirect:/doctors";
        }
        DoctorDto dto = doctorService.findById(id).orElseThrow(() -> new DoctorNotFoundException(id));
        model.addAttribute("doctorDto", dto);
        return "edit-doctors";
    }

    @PostMapping("/{id}/edit")
    public String updateDoctor(@PathVariable(name = "id")Long id,DoctorDto dto){
        doctorService.update(dto);
        return "redirect:/doctors/{id}";
    }

    @PostMapping("/{id}/remove")
    public String removeDoctor(@PathVariable(name = "id")Long id,DoctorDto dto){
        doctorService.remove(dto);
        return "redirect:/doctors";
    }


}
