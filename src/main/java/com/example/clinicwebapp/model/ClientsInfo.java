package com.example.clinicwebapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "clients_info", schema = "clinic_schema")
public class ClientsInfo {
    @Id
    @Column(name = "client_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clients_info_client_id_seq")
    @SequenceGenerator(name = "clients_info_client_id_seq", schema = "clinic_schema", allocationSize = 1)
    private Long id;
    @Column(name = "birthday")
    private Date birthday;
    @Column(name = "address")
    private String address;
    @Column(name = "sex")
    private int sex;
}
