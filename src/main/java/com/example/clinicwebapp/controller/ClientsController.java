package com.example.clinicwebapp.controller;

import com.example.clinicwebapp.exc.ClientNotFoundException;
import com.example.clinicwebapp.exc.DoctorNotFoundException;
import com.example.clinicwebapp.model.dto.ClientDto;
import com.example.clinicwebapp.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientsController {

    private final ClientService clientService;

    @GetMapping
    public String index(Model model) {
        List<ClientDto> list = clientService.findAll();
        model.addAttribute("list", list);
        return "clients";
    }

    @GetMapping("/new")
    public String newClient(ClientDto dto) {
        return "edit-clients";
    }

    @PostMapping("/new")
    public String createClient(@Valid ClientDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-clients";
        }
        clientService.save(dto);
        return "client-created";
    }

    @GetMapping("/{id}")
    public String clientInfo(@PathVariable("id") Long id, Model model) {
        if (!clientService.existById(id)) {
            return "clients"; // клиент не найден сделать;
        }
        ClientDto dto = clientService.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
        model.addAttribute("clientDto", dto);
        return "clients-details";
    }

    @GetMapping("/{id}/edit")
    public String editClient(@PathVariable("id") Long id, Model model) {
        if (!clientService.existById(id)) {
            return "redirect:/clients"; // клиент не найден сделать;
        }
        ClientDto dto = clientService.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
        model.addAttribute("clientDto", dto);
        return "edit-clients";
    }

    @PostMapping("/{id}/edit")
    public String updateClient(@PathVariable(name = "id") Long id, ClientDto dto) {
        clientService.update(dto);
        return "redirect:/clients";
    }

    @PostMapping("/{id}/remove")
    public String removeClient(@PathVariable(name = "id")Long id,ClientDto dto){
        clientService.remove(dto);
        return "redirect:/clients";
    }
}




