package com.apiRest.myFirstApi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity // Indica que esta clase es una entidad JPA, una tabla en base de datos
@Table(name = "users") // Define el nombre de la tabla en la base de datos
@Data // Genera automáticamente los métodos getter, setter, toString, equals, y hashCode
@NoArgsConstructor // Genera un constructor sin argumentos

public class User {
    @Id // Indica que este campo es la clave primaria de la tabla
    @Column(name = "id") // Define el nombre de la columna en la tabla
    @GeneratedValue(strategy = GenerationType.AUTO) // Generación automática del valor de la clave primaria
    private Long id; // Campo para el ID del usuario

    @Column // Por defecto, el nombre de la columna es el mismo que el nombre del campo
    private String name; // Campo para el nombre del usuario

    @Column // Por defecto, el nombre de la columna es el mismo que el nombre del campo
    private String email; // Campo para la dirección de correo electrónico del usuario

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private List<Product> shopList;
}
