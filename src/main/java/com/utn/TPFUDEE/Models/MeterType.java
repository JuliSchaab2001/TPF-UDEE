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
@Table(name = "model_brand")
public class MeterType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model_brand_id")
    private Integer meterType_id;

    @NotNull(message = "El campo model es obligaotorio")
    @Column(name = "model")
    private String model;

    @NotNull(message = "El campo Brand es obligatorio")
    @Column(name = "brand")
    private String brand;

    @OneToMany(mappedBy = "meterType")
    private List<Meter> meterList;

    public void setMeter(List<Meter> meterList){
        this.meterList = meterList;
        this.meterList.forEach(o -> o.setMeterType(this));
    }
}