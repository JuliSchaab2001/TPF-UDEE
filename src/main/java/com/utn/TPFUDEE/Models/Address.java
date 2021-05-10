package com.utn.TPFUDEE.Models;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity

@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Integer address_id;

    @NotNull(message = "El campo Street es obligarorio")
    @Column(name =  "street")
    private String street;

    @NotNull(message = "El campo number es obligatorio") //Agregar validacion para la numeracion de las calles (Mayor a tal, menor a tal)
    @Column(name = "number")
    private Integer number;

    @OneToOne //Googlear por que no mas fetchtype
    @JoinColumn(name = "tariff_id", foreignKey = @ForeignKey(name = "fk_tariff_id"))
    private Tariff tariff;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

}
