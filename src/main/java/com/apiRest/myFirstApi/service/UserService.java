package com.apiRest.myFirstApi.service;

import com.apiRest.myFirstApi.entity.User;
import com.apiRest.myFirstApi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Indica que esta clase es un componente de servicio gestionado por Spring, lógica de negocio
public class UserService {

    @Autowired // Inyecta automáticamente una instancia de UserRepository en esta clase
    private UserRepository userRepository;

    // Crea un nuevo usuario
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Obtiene un usuario por su ID
    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // Encuentra un usuario por su dirección de correo electrónico
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Elimina un usuario por su ID
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Obtiene todos los usuarios
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Actualiza un usuario por su ID
    public User updateUser(Long id, User updatedUser) {
        // Busca un usuario existente por su ID
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            // Actualiza los campos del usuario existente con los valores del usuario actualizado
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());
            // Guarda y devuelve el usuario actualizado
            return userRepository.save(existingUser);
        }
        return null; // Devuelve null si no se encuentra el usuario con el ID proporcionado
    }
}
