package com.utn.TPFUDEE.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;


    @NotNull(message = "El campo userName es obligatorio")
    @Column(name = "userName")
    private String userName;

    @NotNull(message = "El campo password es obligatorio")
    @Column(name = "password")
    private String password;

    //Como vamos a setear esto? desde la bdd??//How to set a default valor? DEA TIRABA COMENTARIO EN INGLES
    @Column(name = "is_employee")
    private boolean isEmployee;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "dni",referencedColumnName = "dni")
    private Client client;

}