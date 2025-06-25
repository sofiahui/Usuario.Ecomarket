package com.Usuario.Usuario.Ecomarket.Service;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Usuario.Usuario.Ecomarket.Modelo.Usuario;
import com.Usuario.Usuario.Ecomarket.Repository.UsuarioRepository;

import java.util.List;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    public Usuario findById(Integer id){
        return usuarioRepository.findById(id).get();
    }

    public Usuario save(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public void deleteById(Integer id){
        usuarioRepository.deleteById(id);
    }
}
