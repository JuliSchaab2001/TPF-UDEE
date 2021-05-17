package com.utn.TPFUDEE.Models;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity

@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer user_id;


    @NotNull(message = "El campo userName es obligatorio")
    @Column(name = "userName")
    private String userName;

    @NotNull(message = "El campo password es obligatorio")
    @Column(name = "password")
    private String password;

    //Como vamos a setear esto? desde la bdd??//How to set a default valor? DEA TIRABA COMENTARIO EN INGLES
    @Column(name = "is_employee")
    private boolean isEmployee;

    @OneToOne
    @JoinColumn(name = "dni",referencedColumnName = "dni")
    private Client client;

}