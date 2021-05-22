package com.utn.TPFUDEE.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Builder
@Table(name = "meters")
public class Meter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meter_id")
    private Integer meter_id;

    @NotNull(message = "El campo es obligatorio")
    @Column(name = "serial_number")
    private String serialNumber;

    @NotNull(message = "El campo es obligatorio")
    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "model_brand_id", referencedColumnName = "model_brand_id")
    private MeterType meterType;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address address;

    @OneToMany(mappedBy = "meter")
    private List<Bill> billList;

    @OneToMany(mappedBy = "meter")
    private List<Measurement> measurementList;


}