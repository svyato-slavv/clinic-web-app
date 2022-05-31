package com.example.clinicwebapp.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "doctors", schema = "clinic_schema")
public class Doctors {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "patronymic")
    private String patronymic;
    @Column(name = "specialization")
    private String specialization;
    @Column(name = "license_for_children")
    private boolean licenseForChildren;
    @OneToMany
    private List<Visits> doctors;

}
