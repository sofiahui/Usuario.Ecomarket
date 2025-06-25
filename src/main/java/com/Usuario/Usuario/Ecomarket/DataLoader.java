package com.Usuario.Usuario.Ecomarket;


import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.Usuario.Usuario.Ecomarket.Modelo.Usuario;
import com.Usuario.Usuario.Ecomarket.Repository.UsuarioRepository;


import java.text.SimpleDateFormat;
import java.util.Date;


@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;
    


   @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < 50; i++) {
            Usuario usuario = new Usuario();

            usuario.setId(i + 1);
            usuario.setRun(faker.idNumber().valid());
            usuario.setNombres(faker.name().firstName());
            usuario.setApellidos(faker.name().lastName());

            Date fecha = faker.date().birthday(18, 65); // fecha de nacimiento entre 18 y 65 años
            usuario.setFechaNacimiento(sdf.format(fecha)); // convertir a String con formato

            usuario.setEmail(faker.internet().emailAddress());

            usuarioRepository.save(usuario);
        }
    }
}
        
