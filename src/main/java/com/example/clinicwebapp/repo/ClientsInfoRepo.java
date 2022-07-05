package com.example.clinicwebapp.repo;

import com.example.clinicwebapp.model.ClientsInfo;
import org.springframework.data.repository.CrudRepository;

public interface ClientsInfoRepo extends CrudRepository<ClientsInfo,Long> {
}
