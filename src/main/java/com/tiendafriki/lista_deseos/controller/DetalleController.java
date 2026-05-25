package com.tiendafriki.lista_deseos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.tiendafriki.lista_deseos.service.DetalleServ;
import com.tiendafriki.lista_deseos.dto.DetalleDTO;
import com.tiendafriki.lista_deseos.model.Detalle;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/Detalle")
public class DetalleController {

    @Autowired
    private DetalleServ s;

    @GetMapping("/listar")
    public ResponseEntity <List <Detalle>> listar() {
        List <Detalle> lista = s.listar();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/buscarxid/{id}")
    public ResponseEntity <Detalle> buscarxId(@PathVariable Integer id) {
        Optional <Detalle> d = s.buscarxID(id);
        if (d.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(d.get());
    }

    @GetMapping("/listadeseos/{listadeseosid}")
    public ResponseEntity <List <Detalle>> buscarxWishlist(@PathVariable Integer wishlistId) {
        List <Detalle> lista = s.buscarxWishlist(wishlistId);
        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/producto/{productoId}")
    public ResponseEntity <List <Detalle>> buscarxProducto(@PathVariable Integer productoId) {
        List <Detalle> lista = s.buscarxProducto(productoId);
        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/agregar")
    public ResponseEntity <String> Agregar(@Valid @RequestBody DetalleDTO dto) {
        return ResponseEntity.status(201).body(s.Guardar(dto));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity <String> Actualizar(@PathVariable Integer id, @Valid @RequestBody DetalleDTO dto) {
        String resultado = s.Actualizar(id, dto);
        if (resultado.contains("No Encontrado")) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(resultado);
    }

    @DeleteMapping("/eliminarxid/{id}")
    public ResponseEntity <String> Eliminar(@PathVariable Integer id) {
        String resultado = s.Eliminar(id);
        if (resultado.contains("No a Sido Encontrado")) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(resultado);
    }

}