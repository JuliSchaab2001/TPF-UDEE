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
    private Integer address_id;

    @NotNull(message = "El campo Street es obligarorio")
    private String street;

    @NotNull(message = "El campo number es obligatorio") //Agregar validacion para la numeracion de las calles (Mayor a tal, menor a tal)
    private Integer number;

    @OneToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "tariff_id")
    private Tariff tariff;

    @ManyToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

}
