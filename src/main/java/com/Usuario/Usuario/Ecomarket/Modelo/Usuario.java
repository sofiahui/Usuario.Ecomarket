package com.Usuario.Usuario.Ecomarket.Modelo;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name="usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id

    private Integer id;
    private String run;
    private String nombres;
    private String apellidos;
    private String fechaNacimiento;
    private String email;



}
