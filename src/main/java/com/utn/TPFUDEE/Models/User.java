package com.utn.TPFUDEE.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;



    @NotNull(message = "El campo userName es obligatorio")
    private String userName;

    @NotNull(message = "El campo password es obligatorio")
    private String password;

    //Como vamos a setear esto? desde la bdd??
    private boolean isAdmin;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

}