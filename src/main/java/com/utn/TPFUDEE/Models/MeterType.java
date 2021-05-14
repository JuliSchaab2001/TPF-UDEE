package com.utn.TPFUDEE.Models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Builder
@Table(name = "model_brand")
public class MeterType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model_brand_id")
    private Integer measurerType_id;

    @NotNull(message = "El campo model es obligaotorio")
    @Column(name = "model")
    private String model;

    @NotNull(message = "El campo Brand es obligatorio")
    @Column(name = "brand")
    private String brand;

    @OneToMany(mappedBy = "meterType")
    private List<Meter> meterList;
}