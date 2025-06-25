package com.Usuario.Usuario.Ecomarket.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Usuario.Usuario.Ecomarket.Modelo.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer >{

}
