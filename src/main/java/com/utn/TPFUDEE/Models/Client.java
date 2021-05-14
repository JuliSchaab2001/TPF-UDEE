package com.utn.TPFUDEE.Models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @NotNull(message = "El campo dni es obligatorio") //Agregar restricciones en cuanto a los valores que puede tomar el dni
    @Column(name = "dni")
    private Integer dni;                             //Como validamos que no se carguen dos DNI iguales?

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

    @OneToOne(mappedBy = "client")
    private User user;


}
