package com.Usuario.Usuario.Ecomarket.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.Usuario.Usuario.Ecomarket.Modelo.Usuario;
import com.Usuario.Usuario.Ecomarket.Repository.UsuarioRepository;
import com.Usuario.Usuario.Ecomarket.Service.UsuarioService;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UsuarioServiceTest {

    @Autowired
    private UsuarioService usuarioService;

    @MockBean
    private UsuarioRepository usuarioRepository;
   @Test
    public void testFindAll() {
        when(usuarioRepository.findAll()).thenReturn(List.of(new Usuario(1, "12.345.678-9", "Juan", "Pérez", "1990-05-15", "juan.perez@example.com")));

        List<Usuario> usuarios = usuarioService.findAll();

        assertNotNull(usuarios);
        assertEquals(1, usuarios.size());
    }

    @Test
    public void testFindById() {
        Integer id = 1;
        Usuario usuario = new Usuario(id, "12.345.678-9", "Juan", "Pérez", "1990-05-15", "juan.perez@example.com");

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        Usuario found = usuarioService.findById(id);

        assertNotNull(found);
        assertEquals("Juan", found.getNombres());
        assertEquals("Pérez", found.getApellidos());
        assertEquals("1990-05-15", found.getFechaNacimiento());
        assertEquals("juan.perez@example.com", found.getEmail());
    }

    @Test
    public void testSave() {
        Usuario usuario = new Usuario(2, "11.111.111-1", "María", "López", "1985-10-20", "maria.lopez@example.com");

        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario saved = usuarioService.save(usuario);

        assertNotNull(saved);
        assertEquals("María", saved.getNombres());
        assertEquals("López", saved.getApellidos());
    }

    @Test
    public void testDeleteById() {
        Integer id = 2;

        doNothing().when(usuarioRepository).deleteById(id);

        usuarioService.deleteById(id);

        verify(usuarioRepository, times(1)).deleteById(id);
    }
}