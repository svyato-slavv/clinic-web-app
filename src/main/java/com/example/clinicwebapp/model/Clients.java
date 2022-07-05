package com.example.clinicwebapp.model;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "clients_id_seq")
    @SequenceGenerator(name = "clients_id_seq",schema = "clinic_schema",allocationSize = 1)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "patronymic")
    private String patronymic;
    @Column(name = "passport_series")
    private Long passportSeries;
    @Column(name = "passport_number")
    private Long passportNumber;
    @Column(name = "snils")
    private String snils;
    @Column(name = "contact_number")
    private String contactNumber;
    @OneToMany(mappedBy = "clients", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Visits> visits;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private ClientsInfo clients_info;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "manager_id")
    private Managers manager;
}
