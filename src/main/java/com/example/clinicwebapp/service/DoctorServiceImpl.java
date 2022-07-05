package com.example.clinicwebapp.service;

import com.example.clinicwebapp.model.Clients;
import com.example.clinicwebapp.model.Doctors;
import com.example.clinicwebapp.model.dto.ClientDto;
import com.example.clinicwebapp.model.dto.DoctorDto;
import com.example.clinicwebapp.repo.DoctorsRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final ModelMapper mapper;
    private final DoctorsRepo doctorsRepo;

    @Override
    public Long save(DoctorDto doctorDto) {
        Doctors doctors = new Doctors();

        doctors.setId(doctorDto.getId());
        doctors.setName(doctorDto.getName());
        doctors.setLastName(doctorDto.getLastName());
        doctors.setPatronymic(doctorDto.getPatronymic());
        doctors.setSpecialization(doctorDto.getSpecialization());
        doctors.setLicenseForChildren(doctorDto.getLicenseForChildren());
        doctors.setContactNumber(doctorDto.getContactNumber());

        doctors = doctorsRepo.save(doctors);

        return doctors.getId();

    }

    @Override
    public void update(DoctorDto doctorDto) {
        Doctors doctors = doctorsRepo.findById(doctorDto.getId()).orElseThrow();
        doctors.setName(doctorDto.getName());
        doctors.setLastName(doctorDto.getLastName());
        doctors.setPatronymic(doctorDto.getPatronymic());
        doctors.setSpecialization(doctorDto.getSpecialization());
        doctors.setLicenseForChildren(doctorDto.getLicenseForChildren());
        doctors.setContactNumber(doctorDto.getContactNumber());

        doctorsRepo.save(doctors);
    }


    @Override
    public void remove(DoctorDto dto) {
        Long id = dto.getId();
        doctorsRepo.deleteById(id);
    }


    @Override
    public boolean existById(Long id) {
        return doctorsRepo.existsById(id);
    }

    @Override
    public Optional<DoctorDto> findById(Long id) {
        return doctorsRepo.findById(id).map(this::map);
    }

    @Override
    public List<DoctorDto> findAll() {
        return Streamable.of(doctorsRepo.findAll()).map(this::map).toList();
    }

    private DoctorDto map(Doctors doctors) {
        return mapper.map(doctors, DoctorDto.class);
    }


}
