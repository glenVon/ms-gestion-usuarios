//https://github.com/glenVon/mic_usuarios
package com.altias.ms_gestion_usuarios.controller;

import com.altias.ms_gestion_usuarios.model.Usuario;
import com.altias.ms_gestion_usuarios.service.ServicioUsuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")//ruta base para los endpoints
//http://localhost:8080/api/v1/users (en POSTMAN)
//SELECT * FROM bd_usuario.user;(en la base de datos bd_usuario)
public class Control {

    private final ServicioUsuario ServicioUsuario;
    

    @Autowired
    public Control(ServicioUsuario servicioUsuario) {
        this.ServicioUsuario = servicioUsuario;
    }

    @PostMapping("/login")
    //http://localhost:8080/api/v1/users/login (en POSTMAN)
    //{"nombreUsuario":"admin","password":"1234"}
    public ResponseEntity<?> login(@RequestBody Usuario user) {
        return ServicioUsuario.login(user);
    }

    @PostMapping("/creador")
    //http://localhost:8080/api/v1/users/creador(en POSTMAN)
    //{"nombre":"Juan","nombreUsuario":"jdoe","password":"1234","apellido_paterno":"Doe","apellido_materno":"Smith","email":"egon@von.cl","fecha_nacimiento":"1992-04-01"}
    public ResponseEntity<Usuario> createUser(@RequestBody Usuario user) {
        return ServicioUsuario.createUser(user);
    }

    @GetMapping("/listar")
    //http://localhost:8080/api/v1/users/listar (en POSTMAN)
    //retorna todos los usuarios
    public ResponseEntity<List<Usuario>> getAllUsers() {
        return ServicioUsuario.getAllUsers();
    }
    
    @GetMapping("/usuarioPorId/{id}")
    //http://localhost:8080/api/v1/users/usuarioPorId/1 (en POSTMAN)
    //retorna un usuario por su id
    public ResponseEntity<Usuario> getUserById(@PathVariable Long id) {
        return ServicioUsuario.getUserById(id);
    }

    //eliminar usuario
    @DeleteMapping("/eliminar/{id}")
    //http://localhost:8080/api/v1/users/eliminar/1 (en POSTMAN)
    //elimina un usuario por su id
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return ServicioUsuario.deleteUser(id);
    }
  
    @GetMapping("/datos-protegidos")
    //http://localhost:8080/api/v1/users/datos-protegidos?nombreUsuario=admin&password=1234
    public ResponseEntity<?> getDatosProtegidos(@RequestParam String nombreUsuario, @RequestParam String password) {
        Usuario user = new Usuario();
        user.setNombreUsuario(nombreUsuario);
        user.setPassword(password);
        return ServicioUsuario.login(user);
    }

}