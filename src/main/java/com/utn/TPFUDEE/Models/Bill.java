package com.utn.TPFUDEE.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@Entity
public class Bill {
    private Measurer measurer;
    private Measurement initialMeasurement;
    private Measurement finalMeasurement;
    private Double kwConsumed;
    private  boolean isPaid;
}
