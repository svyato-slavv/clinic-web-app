package com.example.clinicwebapp.exc;

import lombok.Value;

@Value
public class DoctorNotFoundException extends RuntimeException {
    Long id;
}
