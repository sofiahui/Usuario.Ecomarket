package com.Usuario.Usuario.Ecomarket.Controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.Usuario.Usuario.Ecomarket.Modelo.Usuario;
import com.Usuario.Usuario.Ecomarket.Service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("api/v1/usuarios")
@Tag(name = "Usuarios", description = "Operaciones relacionadas con los usuarios")

public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @Operation(summary = "Obtener todos los usuarios", description = "Obtiene una lista de todos los usuarios")
    public List<Usuario> getAllUsuarios() {
        return usuarioService.findAll();
    }
    
     @GetMapping("/{id}")
    @Operation(summary = "Obtener un usuario por ID", description = "Retorna un usuario según su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario encontrado",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Usuario.class))),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })

    public Usuario getUsuarioById(@PathVariable Integer id) {
        return usuarioService.findById(id);
    }


    @PostMapping
    @Operation(summary = "Crear un nuevo usuario", description = "Registra un nuevo usuario en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Usuario.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
          })


    
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }

    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un usuario",description = "Actualiza un usuario existente")
    @ApiResponses(value = {
         @ApiResponse (responseCode = "200", description = "usuario actualizado exitosamente",
                 content = @Content(mediaType = "application/json",
                 schema = @Schema (implementation = Usuario.class))),
                 @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    
    
    public Usuario updateUsuario(@PathVariable Integer id, @RequestBody Usuario usuario){
        usuario.setId(id);
        return usuarioService.save(usuario);
    }



    @DeleteMapping("{id}")
    @Operation(summary = "Eliminar un usuario", description = "Eliminar un usuario por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Usuario eliminado exitosamente" ),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado ")
    })
   
    public void deleteUsuario(@PathVariable Integer id ){
        usuarioService.deleteById(id);
     }

} 