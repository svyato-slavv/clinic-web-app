package com.example.clinicwebapp.service;

import com.example.clinicwebapp.model.dto.ClientDto;
import com.example.clinicwebapp.model.dto.DoctorDto;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;

public interface DoctorService {

    Long save(DoctorDto dto);

    void update(DoctorDto dto);

    void remove(DoctorDto dto);

    List<DoctorDto> findAll();

    Optional<DoctorDto> findById(Long id);

    boolean existById(Long id);
}
