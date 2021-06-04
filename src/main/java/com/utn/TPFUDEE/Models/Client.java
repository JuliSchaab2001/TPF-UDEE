package com.utn.TPFUDEE.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @NotNull(message = "El campo dni es obligatorio") //Agregar restricciones en cuanto a los valores que puede tomar el dni
    @Column(name = "dni")
    private Integer dni;

    @Column(name = "name")
    @NotNull(message = "El campo name es obligatorio")
    private String name;

    @Column(name = "mail")
    @NotNull(message = "El campo mail es obligatorio")
    private String mail;

    @Column(name = "last_name")
    @NotNull(message = "El campo lastName es obligatorio")
    private String lastName;


    @OneToMany(mappedBy = "client")
    private List<Address> addressList;

    @JsonIgnore
    @OneToOne(mappedBy = "client")
    private User user;

    public void setAddress(List<Address> addressList){
        this.addressList = addressList;
        this.addressList.forEach(o -> o.setClient(this));
    }



}
