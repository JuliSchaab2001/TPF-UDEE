package com.utn.TPFUDEE.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Measurer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer serialNumber;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "measurer_id")
    private MeasurerType measurerType;
}
