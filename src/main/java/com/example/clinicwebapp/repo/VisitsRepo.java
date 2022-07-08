package com.example.clinicwebapp.repo;

import com.example.clinicwebapp.model.Visits;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VisitsRepo extends CrudRepository<Visits,Long> {
    List<Visits> findAllByClientId(Long id);

    List<Visits> findAllByDoctorId(Long id);

}
