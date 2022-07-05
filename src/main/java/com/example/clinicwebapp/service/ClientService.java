package com.example.clinicwebapp.service;

import com.example.clinicwebapp.model.dto.ClientDto;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    Long save(ClientDto clientDto);

    void update(ClientDto dto);

    List<ClientDto> findAll();


    Optional<ClientDto> findById(Long id);

    boolean existById(Long id);

    void remove(ClientDto dto);
}
