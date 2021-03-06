package com.utn.TPFUDEE.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Builder
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Integer addressId;

    @NotNull(message = "El campo Street es obligarorio")
    @Column(name =  "street")
    private String street;

    @NotNull(message = "El campo number es obligatorio") //Agregar validacion para la numeracion de las calles (Mayor a tal, menor a tal)
    @Column(name = "number")
    private Integer number;

    @ManyToOne
    @JoinColumn(name = "tariff_id", referencedColumnName = "tariff_id")
    private Tariff tariff;


    @ManyToOne
    @JoinColumn(name = "dni", referencedColumnName = "dni")
    private Client client;

    @JsonIgnore
    @OneToOne(mappedBy = "address")
    private Meter meter;


}
