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
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "doctors_id_seq")
    @SequenceGenerator(name = "doctors_id_seq",schema = "clinic_schema",allocationSize = 1)
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
    private String licenseForChildren;
    @Column(name = "contact_number")
    private String contactNumber;
    @OneToMany(mappedBy = "doctorId",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Visits> doctors;

}
