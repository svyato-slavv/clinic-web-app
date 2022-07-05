package com.example.clinicwebapp.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "role", schema = "clinic_schema")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_id_seq")
    @SequenceGenerator(name = "roles_id_seq", schema = "clinic_schema", allocationSize = 1)
    private Long id;
    @Column
    private String name;
}
