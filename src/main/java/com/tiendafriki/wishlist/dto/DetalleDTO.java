package com.tiendafriki.wishlist.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleDTO {

    @NotNull(message = "[+] El ID De La Wishlist No Puede Ser Nulo [>_<] ... ")
    private Integer WishlistID;

    @NotNull(message = "[+] El ID Del Producto No Puede Ser Nulo [>_<] ... ")
    private Integer ProductoID;

    @NotBlank(message = "[+] El Nombre Del Producto No Puede Quedar Vacio [>_<] ... ")
    private String Producto;

    @Min(value = 5000, message = "[+] El Precio Debe Ser Mayor a $5.000 [>_<] ... ")
    @NotNull(message = "[+] El Precio No Puede Quedar Nulo [>_<] ... ")
    private Integer Precio;

}