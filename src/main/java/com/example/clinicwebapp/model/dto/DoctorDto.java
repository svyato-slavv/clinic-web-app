package com.example.clinicwebapp.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DoctorDto {

    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String lastName;
    @NotBlank
    private String patronymic;
    @NotBlank
    private String specialization;
    @NotBlank
    private String licenseForChildren;
    @NotBlank
    private String contactNumber;

}
