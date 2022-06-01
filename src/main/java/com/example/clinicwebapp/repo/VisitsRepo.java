package com.example.clinicwebapp.repo;

import com.example.clinicwebapp.model.Visits;
import org.springframework.data.repository.CrudRepository;

public interface VisitsRepo extends CrudRepository<Visits,Long> {
}
