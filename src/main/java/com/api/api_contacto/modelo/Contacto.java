package com.api.api_contacto.modelo;


import com.api.api_contacto.utils.CategoriaContacto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contactos")
public class Contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;

    private String apellidos;

    private String telefono;

    private String correo;

    private Boolean favorito = false;

    private String direccion;

    @Column(name = "color_avatar")
    private String colorAvatar;

    @Enumerated(EnumType.STRING)
    private CategoriaContacto categoria;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}