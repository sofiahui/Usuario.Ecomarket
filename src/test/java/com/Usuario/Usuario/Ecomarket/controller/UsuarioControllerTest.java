package com.Usuario.Usuario.Ecomarket.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


import com.Usuario.Usuario.Ecomarket.Controller.UsuarioController;
import com.Usuario.Usuario.Ecomarket.Modelo.Usuario;
import com.Usuario.Usuario.Ecomarket.Service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;


@WebMvcTest(UsuarioController.class) 
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc; // Proporciona una manera de realizar peticiones HTTP en las pruebas

    @MockBean
    private UsuarioService usuarioService; 

    @Autowired
    private ObjectMapper objectMapper; // Se usa para convertir objetos Java a JSON y viceversa

    private Usuario usuario;

     @BeforeEach
    void setUp() throws Exception {
        usuario = new Usuario();
        usuario.setId(1);
        usuario.setRun("12345678-9");
        usuario.setNombres("Juan");
        usuario.setApellidos("Pérez Soto");
        usuario.setFechaNacimiento("2000-01-15");
        usuario.setEmail("juan.perez@example.com");
    }

    @Test
    public void testGetUsuarios() throws Exception {
        when(usuarioService.findAll()).thenReturn(List.of(usuario));

        mockMvc.perform(get("/api/v1/usuario"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].run").value("12345678-9"))
                .andExpect(jsonPath("$[0].nombres").value("Juan"))
                .andExpect(jsonPath("$[0].apellidos").value("Pérez Soto"))
                .andExpect(jsonPath("$[0].fechaNacimiento").value("2000-01-15"))
                .andExpect(jsonPath("$[0].email").value("juan.perez@example.com"));
    }

    @Test
    public void testGetUsuarioById() throws Exception {
        when(usuarioService.findById(1)).thenReturn(usuario);

        mockMvc.perform(get("/api/v1/usuario/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.run").value("12345678-9"))
                .andExpect(jsonPath("$.nombres").value("Juan"))
                .andExpect(jsonPath("$.apellidos").value("Pérez Soto"))
                .andExpect(jsonPath("$.fechaNacimiento").value("2000-01-15"))
                .andExpect(jsonPath("$.email").value("juan.perez@example.com"));
    }

    @Test
    public void testCreateUsuario() throws Exception {
        when(usuarioService.save(any(Usuario.class))).thenReturn(usuario);

        mockMvc.perform(post("/api/v1/usuario")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.run").value("12345678-9"))
                .andExpect(jsonPath("$.nombres").value("Juan"))
                .andExpect(jsonPath("$.apellidos").value("Pérez Soto"))
                .andExpect(jsonPath("$.fechaNacimiento").value("2000-01-15"))
                .andExpect(jsonPath("$.email").value("juan.perez@example.com"));
    }

    @Test
    public void testUpdateUsuario() throws Exception {
        when(usuarioService.save(any(Usuario.class))).thenReturn(usuario);

        mockMvc.perform(put("/api/v1/usuario/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.run").value("12345678-9"))
                .andExpect(jsonPath("$.nombres").value("Juan"))
                .andExpect(jsonPath("$.apellidos").value("Pérez Soto"))
                .andExpect(jsonPath("$.fechaNacimiento").value("2000-01-15"))
                .andExpect(jsonPath("$.email").value("juan.perez@example.com"));
    }

    @Test
    public void testDeleteUsuario() throws Exception {
        doNothing().when(usuarioService).deleteById(1);

        mockMvc.perform(delete("/api/v1/usuario/1"))
                .andExpect(status().isOk());

        verify(usuarioService, times(1)).deleteById(1);
    }

    @Test
    public void testGetUsuarioById_NotFound() throws Exception {
        when(usuarioService.findById(99)).thenReturn(null);

        mockMvc.perform(get("/api/v1/usuario/99"))
                .andExpect(status().isNotFound());
    }
}

