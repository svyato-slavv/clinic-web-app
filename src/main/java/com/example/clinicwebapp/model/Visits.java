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
    private Long id;
    @Column(name = "date_and_time")
    private Timestamp dateAndTime;
    @Column(name = "doctor_id")
    private Long doctorId;
    @Column(name = "client_id")
    private Long clientId;
    @Column(name = "survey_result")
    private String surveyResult;
    @ManyToOne
    private Clients clients;
    @ManyToOne
    private Doctors doctors;


}
