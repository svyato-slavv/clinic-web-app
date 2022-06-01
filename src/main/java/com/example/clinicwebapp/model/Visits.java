package com.example.clinicwebapp.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "visits", schema = "clinic_schema")
public class Visits {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "visits_id_seq")
    @SequenceGenerator(name = "visits_id_seq",schema = "edu_schema",allocationSize = 1)
    private Long id;
    @Column(name = "date_and_time")
    private Timestamp dateAndTime;
//    @Column(name = "doctor_id")
//    private Long doctorId;
//    @Column(name = "client_id")
//    private Long clientId;
    @Column(name = "survey_result")
    private String surveyResult;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Clients clients;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id")
    private Doctors doctors;


}
