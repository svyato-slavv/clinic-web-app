package com.example.clinicwebapp.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "clients", schema = "clinic_schema")
public class Clients {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "patronymic")
    private String patronymic;
    @Column(name = "passport_series")
    private int passportSeries;
    @Column(name = "passport_number")
    private int passportNumber;
    @Column(name = "snils")
    private int snils;
    @Column(name = "manager_id")
    private Long manager_id;
    @OneToMany
    private List<Visits> visits;
    @OneToOne
    private Clients_info clients_info;
    @ManyToOne
    private Managers manager;
}
