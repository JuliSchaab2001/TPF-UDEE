package com.utn.TPFUDEE.Models;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "bills")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    private Integer bill_id;

    @Column(name = "initial_measurement")
    @NotNull
    private Integer initialMeasurement;

    @Column(name = "final_measurement")
    @NotNull
    private Integer finalMeasurement;

    @Column(name = "initial_date")
    @NotNull
    private LocalDateTime initialDate;

    @Column(name = "final_date")
    @NotNull
    private LocalDateTime finalDate;

    @Column(name = "consumed_kw")
    @NotNull
    private Double kwConsumed;

    @Column(name = "total")
    @NotNull
    private double total;

    @Column(name = "is_paid")
    @NotNull
    private Boolean isPaid;

    @ManyToOne
    @JoinColumn(name = "meter_id", referencedColumnName = "meter_id")
    private Meter meter;

    @OneToMany(mappedBy = "bill")
    private List<Measurement> measurementList;









}
