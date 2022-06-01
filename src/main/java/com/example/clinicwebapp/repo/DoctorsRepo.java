package com.example.clinicwebapp.repo;

import com.example.clinicwebapp.model.Doctors;
import org.springframework.data.repository.CrudRepository;

public interface DoctorsRepo extends CrudRepository<Doctors,Long> {
}
