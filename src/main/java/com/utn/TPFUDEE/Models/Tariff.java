package com.utn.TPFUDEE.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tariffs")
public class Tariff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tariff_id")
    private Integer tariff_id;

    @NotNull(message = "El campo es obligatorio")
    @Column(name = "type")
    private String type;

    @NotNull(message = "El campo es obligatorio")
    @Column(name = "price")
    private Double price;

    @NotNull(message = "El campo es obligatorio")
    @OneToMany(mappedBy = "tariffs")
    private List<Address> address;
}