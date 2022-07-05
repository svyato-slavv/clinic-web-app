package com.example.clinicwebapp.repo;

import com.example.clinicwebapp.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsersRepo extends CrudRepository<User,Long> {

    Optional<User>  findByEmail(String email);
}
