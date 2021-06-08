package com.utn.TPFUDEE.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Builder
@Table(name = "bills")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    private Integer billId;

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


    @Column(name = "expiration_date")
    @NotNull
    private LocalDateTime expirationDate;

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

    public void setMeasurement(List<Measurement> measurementList){
        this.measurementList = measurementList;
        this.measurementList.forEach(o -> o.setBill(this));
    }







}
