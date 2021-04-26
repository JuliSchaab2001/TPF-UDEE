package com.utn.TPFUDEE.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
public class Client {

    @Id
    @NotNull(message = "El campo dni es obligatorio") //Agregar restricciones en cuanto a los valores que puede tomar el dni
    private Integer dni;                              //Como validamos que no se carguen dos DNI iguales?

    @NotNull(message = "El campo name es obligatorio")
    private String name;

    @NotNull(message = "El campo lastName es obligatorio")
    private String lastName;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Address address;


}
