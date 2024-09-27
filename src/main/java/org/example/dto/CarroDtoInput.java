package org.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarroDtoInput {
    private int id;
    private String modelo;
    private String placa;
    private String chassi;
}
