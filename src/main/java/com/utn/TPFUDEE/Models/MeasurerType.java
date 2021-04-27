package com.utn.TPFUDEE.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
public class MeasurerType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer measurerType_id;

    @NotNull(message = "El campo model es obligaotorio")
    private String model;

    @NotNull(message = "El campo Brand es obligatorio")
    private String brand;
}
