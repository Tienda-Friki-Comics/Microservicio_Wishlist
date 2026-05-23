package com.tiendafriki.wishlist.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishlistDTO {

    @NotBlank(message = "[+] El Nombre No Puede Quedar Vacio [>_<] ... ")
    private String Nombre;

}