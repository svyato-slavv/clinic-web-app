package com.example.clinicwebapp.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "clients_info", schema = "clinic_schema")
public class Clients_info {
    @Id
    @Column(name = "client_id")
    private Long id;
    @Column(name = "contact_number")
    private String contact_number;
    @Column(name = "birthday")
    private Date birthday;
    @Column(name = "address")
    private String address;
    @Column(name = "sex")
    private int sex;
    @OneToOne
    private Clients client;
}
