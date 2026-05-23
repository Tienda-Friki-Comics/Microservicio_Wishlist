package com.tiendafriki.wishlist.controller;

import org.springframework.beans.factory.annotation.*;
import com.tiendafriki.wishlist.service.WishlistServ;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.tiendafriki.wishlist.dto.WishlistDTO;
import com.tiendafriki.wishlist.model.Wishlist;
import jakarta.validation.*;
import java.util.*;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    private WishlistServ s;

    @GetMapping("/listar")
    public ResponseEntity <List <Wishlist>> listar() {
        List <Wishlist> lista = s.listar();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/buscarxid/{id}")
    public ResponseEntity <Wishlist> buscarxID(@PathVariable Integer id) {
        Optional <Wishlist> w = s.buscarxID(id);
        if (w.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(w.get());
    }

    @GetMapping("/buscarxnombre/{nombre}")
    public ResponseEntity <List <Wishlist>> buscarxNombre(@PathVariable String nombre) {
        List <Wishlist> lista = s.buscarxNombre(nombre);
        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/crear")
    public ResponseEntity <String> Crear(@Valid @RequestBody WishlistDTO dto) {
        return ResponseEntity.status(201).body(s.Guardar(dto));
    }

    @PostMapping("/actualizar/{id}")
    public ResponseEntity <String> Actualizar(@PathVariable Integer id, @Valid @RequestBody WishlistDTO dto) {
        String resultado = s.Actualizar(id, dto);
        if (resultado.contains("No Encontrada")) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(resultado);
    }

    @DeleteMapping("/eliminarxid/{id}")
    public ResponseEntity <String> Eliminar(@PathVariable Integer id) {
        String resultado = s.Eliminar(id);
        if (resultado.contains("No a Sido Encontrada")) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(resultado);
    }

}