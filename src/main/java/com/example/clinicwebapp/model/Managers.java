package com.example.clinicwebapp.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "managers", schema = "clinic_schema")
public class Managers {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "managers_id_seq")
    @SequenceGenerator(name = "managers_id_seq",schema = "edu_schema",allocationSize = 1)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "patronymic")
    private String patronymic;
    @Column(name = "contact_number")
    private String contactNumber;
    @OneToMany(mappedBy = "manager", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Clients> clients;


}
