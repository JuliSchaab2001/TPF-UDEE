package com.utn.TPFUDEE.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table()
public class Meter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer serialNumber;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "measurerType_id")
    private MeasurerType measurerType;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address address;
}
