package com.tiendafriki.lista_deseos.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListaDeseosDTO {

    @NotBlank(message = "[+] El Rut No Puede Quedar Vacio [>_<] ... ")
    private String Rut;

}