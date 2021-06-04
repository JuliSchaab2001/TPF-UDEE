package com.utn.TPFUDEE.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Builder
@Table(name = "measurements")
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "measurements_id")
    private Integer measurementId;

    @NotNull(message = "El campo es obligatorio")
    @Column(name = "date")
    private String date;

    @NotNull(message = "El campo es obligatorio")
    @Column(name = "kw")
    private Float kw;

    @ManyToOne
    @JoinColumn(name = "meter_id", referencedColumnName = "meter_id")
    private Meter meter;

    @ManyToOne
    @JoinColumn(name = "bill_id", referencedColumnName = "bill_id")
    private Bill bill;








}
