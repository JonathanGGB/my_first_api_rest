package com.apiRest.myFirstApi.controller;

import com.apiRest.myFirstApi.entity.Product;
import com.apiRest.myFirstApi.entity.User;
import com.apiRest.myFirstApi.exception.UserNotFoundException;
import com.apiRest.myFirstApi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Marca la clase como un controlador REST
@RequestMapping("/users") // Define la raíz de URL para todas las rutas manejadas por este controlador
@Tag(name = "Users") // Etiqueta para documentación Swagger
@CrossOrigin(origins="*")
public class UserController {

    @Autowired // Realiza la inyección automática de la dependencia UserService
    private UserService userService;

    // Crea un nuevo usuario
    @Operation(summary = "Create a new user") // Descripción de la operación para Swagger
    @PostMapping("/create") // Mapeo para manejar solicitudes POST en la ruta /users/create
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // Obtiene un usuario por su ID
    @Operation(summary = "Get a user by ID") // Descripción de la operación para Swagger
    @GetMapping("/{id}") // Mapeo para manejar solicitudes GET en la ruta /users/{id}
    public User getUser(@PathVariable Long id) {
        System.out.println(id);
        if (id <= 0) {
            throw new UserNotFoundException("El usuario no se encontró con el ID: " + id);
        }
        System.out.println("NO ENTRO");
        return userService.getUser(id);
    }

    // Encuentra un usuario por su dirección de correo electrónico
    @Operation(summary = "Find a user by email") // Descripción de la operación para Swagger
    @GetMapping("/findByEmail") // Mapeo para manejar solicitudes GET en la ruta /users/findByEmail
    public User findByEmail(@RequestParam String email) {
        return userService.findByEmail(email);
    }

    // Actualiza un usuario por su ID
    @Operation(summary = "Update a user by ID") // Descripción de la operación para Swagger
    @PutMapping("/{id}") // Mapeo para manejar solicitudes PUT en la ruta /users/{id}
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser);
    }

    // Elimina un usuario por su ID
    @Operation(summary = "Delete a user by ID") // Descripción de la operación para Swagger
    @DeleteMapping("/{id}") // Mapeo para manejar solicitudes DELETE en la ruta /users/{id}
    public boolean deleteUser(@PathVariable Long id) {
        if (userService.deleteUser(id)){
            return true;
        }
        return false;
    }

    // Obtiene todos los usuarios
    @Operation(summary = "Get all users") // Descripción de la operación para Swagger
    @GetMapping("/getAll") // Mapeo para manejar solicitudes GET en la ruta /users/getAll
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @Operation(summary = "Get the product list of a user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de productos del usuario",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Product.class),
                            examples = @ExampleObject(value = "[{\"id\": 1, \"name\": \"Producto 1\", \"cost\": 10.0, \"quantity\": 5}]")
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuario no encontrado",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(value = "Usuario no encontrado")
                    )
            )
    })
    @GetMapping("/{id}/shop_list")
    public ResponseEntity<?> getUserShopList(@PathVariable Long id){
        User user = userService.getUser(id);
        if(user != null){
            List<Product> products = user.getShopList();
            return ResponseEntity.ok(products);
        } else{
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
}
