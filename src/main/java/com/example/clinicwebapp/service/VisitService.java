package com.example.clinicwebapp.service;

import com.example.clinicwebapp.model.dto.VisitDto;

import java.util.List;
import java.util.Optional;

public interface VisitService {

    void save(VisitDto visitDto);

    void update(VisitDto visitDto);

    void remove(Long id);

//    boolean existById(Long id);

    Optional<VisitDto> findById(Long id);

    List<VisitDto> findAllForClientAfter(Long id);

    List<VisitDto> findAllForClientBefore(Long id);

    List<VisitDto> findAllForDoctorAfter(Long id);

    List<VisitDto> findAllForDoctorBefore(Long id);



    List<VisitDto> findAll();
}
