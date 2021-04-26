package com.utn.TPFUDEE.Models;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "El campo Street es obligarorio")
    private String street;

    @NotNull(message = "El campo number es obligatorio") //Agregar validacion para la numeracion de las calles (Mayor a tal, menor a tal)
    private Integer number;

    @OneToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Tariff tariff;

    @OneToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Measurement measurement;

}
