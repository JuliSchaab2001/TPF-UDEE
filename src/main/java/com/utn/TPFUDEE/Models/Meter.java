package com.utn.TPFUDEE.Models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.mapping.Join;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "meters")
public class Meter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer serialNumber;


    @ManyToOne
    @JoinColumn(name = "model_brand_id", referencedColumnName = "model_brand_id")
    private MeterType meterType;

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address address;

    @OneToMany(mappedBy = "meters")
    private List<Bill> billList;

    @OneToMany(mappedBy = "meters")
    private List<Measurement> measurementList;


}