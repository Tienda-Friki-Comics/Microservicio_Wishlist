package com.tiendafriki.wishlist.model;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "Detalle")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data

public class WishlistDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @ManyToOne
    @JoinColumn(name = "wishlist_id", nullable = false)
    private Wishlist Wishlist;

    @NotNull(message = "[+] El ID Del Producto No Puede Ser Nulo [>_<] ... ")
    @Column(nullable = false)
    private Integer ProductoID;

    @NotBlank(message = "[+] El Nombre Del Producto No Puede Quedar Vacio [>_<] ... ")
    @Column(nullable = false, length = 150)
    private String Producto;

    @Min(value = 5000, message = "[+] El Precio Debe Ser Mayor a $5.000 [>_<] ... ")
    @Column(nullable = false)
    private Integer Precio;

}