package com.example.clinicwebapp.repo;

import com.example.clinicwebapp.model.Clients;
import org.springframework.data.repository.CrudRepository;

public interface ClientsRepo extends CrudRepository<Clients,Long> {
}
