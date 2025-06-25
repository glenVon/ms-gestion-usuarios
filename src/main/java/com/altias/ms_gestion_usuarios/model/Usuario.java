package com.altias.ms_gestion_usuarios.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.GenerationType;

import jakarta.persistence.*;


@Table(name = "usuario")
@Entity
@Data


public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String nombreUsuario;
    private String password;
    private String apellido_paterno;
    private String apellido_materno;
    private String email;
    private LocalDate fecha_nacimiento;

}
