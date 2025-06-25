package com.altias.ms_gestion_usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.altias.ms_gestion_usuarios.model.Usuario;

public interface UserRepository extends JpaRepository<Usuario, Long> { 
    Usuario findByNombreUsuarioAndPassword(String nombreUsuario, String password);

}