package com.example.clinicwebapp.exc;

import lombok.Getter;
import lombok.Value;

@Value
public class ClientNotFoundException extends RuntimeException{
    Long id;
}
