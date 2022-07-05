package com.example.clinicwebapp.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ClientDto {
    private Long id;
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    @NotBlank
    private String patronymic;
    private Long passportSeries;
    private Long passportNumber;
    @NotBlank
    private String snils;
    @NotBlank
    private String contactNumber;
}
