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
    private Integer id;



    @NotNull(message = "El campo userName es obligatorio")
    private String userName;

    @NotNull(message = "El campo password es obligatorio")
    private String password;

    //Como vamos a setear esto? desde la bdd??
    private boolean isAdmin;

    @JoinColumn(name = "user_id")
    @OneToOne(fetch = FetchType.EAGER)
    private Client client;

    /*@JoinColumn(name = "player_id")
    @OneToOne(fetch = FetchType.EAGER)
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private List<Instrument> instrumentList;*/


}
