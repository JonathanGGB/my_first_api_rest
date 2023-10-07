package com.apiRest.myFirstApi.repository;

import com.apiRest.myFirstApi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

// Extiende JpaRepository para manejar operaciones de base de datos para la entidad User
public interface UserRepository extends JpaRepository<User, Long> {

    // Método personalizado para buscar un usuario por su dirección de correo electrónico
    User findByEmail(String email);
}
