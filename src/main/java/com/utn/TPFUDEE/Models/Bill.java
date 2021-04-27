package com.utn.TPFUDEE.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bill_id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "measurer_id")
    private Measurer measurer;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "initial_measurement_id")
    private Measurement initialMeasurement;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "final_measurement_id")
    private Measurement finalMeasurement;

    private Double kwConsumed;
    private  boolean isPaid;





}
