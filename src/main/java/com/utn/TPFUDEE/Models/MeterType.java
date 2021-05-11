package com.utn.TPFUDEE.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "model_brand")
public class MeterType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer measurerType_id;

    @NotNull(message = "El campo model es obligaotorio")
    @Column(name = "model")
    private String model;

    @NotNull(message = "El campo Brand es obligatorio")
    @Column(name = "brand")
    private String brand;

    @OneToMany(mappedBy = "model_brand")
    private List<Meter> meterList;
}