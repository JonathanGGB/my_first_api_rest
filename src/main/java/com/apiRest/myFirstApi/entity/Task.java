package com.apiRest.myFirstApi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @Column
    @NotBlank(message = "El título de la tarea no puede estar en blanco")
    private String title;

    @Column
    private String description;

    @Column
    @Min(value = 0, message = "El valor mínimo debe ser 0")
    @Max(value = 100, message = "El valor máximo es 100")
    private int grade;
}
