package com.utn.TPFUDEE.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "tariffs")
public class Tariff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tariff_id")
    private Integer tariff_id;

    @NotNull(message = "El campo es obligatorio")
    @Column(name = "type")
    private String type;

    @NotNull(message = "El campo es obligatorio")
    @Column(name = "price")
    private Double price;

    @JsonIgnore
    @OneToMany(mappedBy = "tariff")
    private List<Address> addressList;

    public void setAddress(List<Address> addressList){
        this.addressList = addressList;
        this.addressList.forEach(o -> o.setTariff(this));
    }
}