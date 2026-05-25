package com.tiendafriki.lista_deseos.model;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import java.util.*;
import java.time.*;
import lombok.*;

@Table(name = "Wishlist")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data

public class ListaDeseos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @NotBlank(message = "[+] El Nombre No Puede Estar Vacio [>_<] ... ")
    @Column(nullable = false, length = 12)
    private String Rut;

    @Column(nullable = false)
    private LocalDateTime Fecha;

    @OneToMany(mappedBy = "Wishlist", cascade = CascadeType.ALL)
    private List <Detalle> Detalle;

}