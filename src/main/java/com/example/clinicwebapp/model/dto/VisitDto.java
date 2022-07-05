package com.example.clinicwebapp.model.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class VisitDto {

    private Long id;
    private String dateAndTime;
    private String fullClientInfo;
    private String fullDoctorInfo;
    private String surveyResult;

}
