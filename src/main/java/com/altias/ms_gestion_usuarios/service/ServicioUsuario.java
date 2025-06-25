package com.altias.ms_gestion_usuarios.service;

import com.altias.ms_gestion_usuarios.repository.UserRepository;
import com.altias.ms_gestion_usuarios.model.Usuario;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioUsuario {

    private final UserRepository userRepository;
    

    @Autowired
    public ServicioUsuario(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public ResponseEntity<?> login(Usuario user) {
        Usuario authenticatedUser = userRepository.findByNombreUsuarioAndPassword(user.getNombreUsuario(), user.getPassword());
        if (authenticatedUser != null) {
            return ResponseEntity.ok(authenticatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No autorizado");
        }
    }

    public ResponseEntity<Usuario> createUser(Usuario user) {
        Usuario savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
        
    }

    public ResponseEntity<List<Usuario>> getAllUsers() {
        List<Usuario> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    public ResponseEntity<Usuario> getUserById(Long id) {
        Usuario user = userRepository.findById(id).orElse(null);
        if (user != null) {
            //retorna solo email y nombre
            Usuario userResponse = new Usuario();

            userResponse.setNombre(user.getNombre());
            userResponse.setNombreUsuario(user.getNombreUsuario());
            userResponse.setApellido_paterno(user.getApellido_paterno());
            userResponse.setApellido_materno(user.getApellido_materno());
            userResponse.setEmail(user.getEmail());
            userResponse.setFecha_nacimiento(user.getFecha_nacimiento());
            userResponse.setId(user.getId());
            return ResponseEntity.ok(userResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<String> deleteUser(Long id) {
    if (userRepository.existsById(id)) {
        userRepository.deleteById(id);  // Elimina directamente por ID
        return ResponseEntity.ok("Usuario con ID " + id + " eliminado");
    } else {
        return ResponseEntity.status(404).body("Usuario no encontrado");
    }
    }

    




}
