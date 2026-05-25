package com.tiendafriki.lista_deseos.controller;

import com.tiendafriki.lista_deseos.service.ListaDeseosServ;
import com.tiendafriki.lista_deseos.dto.ListaDeseosDTO;
import org.springframework.beans.factory.annotation.*;
import com.tiendafriki.lista_deseos.model.ListaDeseos;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.validation.*;
import java.util.*;

@RestController
@RequestMapping("/wishlist")
public class ListaDeseosController {

    @Autowired
    private ListaDeseosServ s;

    @GetMapping("/listar")
    public ResponseEntity <List <ListaDeseos>> listar() {
        List <ListaDeseos> lista = s.listar();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/buscarxid/{id}")
    public ResponseEntity <ListaDeseos> buscarxID(@PathVariable Integer id) {
        Optional <ListaDeseos> w = s.buscarxID(id);
        if (w.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(w.get());
    }

    @GetMapping("/buscarxrut/{rut}")
    public ResponseEntity <ListaDeseos> buscarxNombre(@PathVariable String rut) {
        Optional <ListaDeseos> lista = s.buscarxRut(rut);
        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista.get());
    }

    @PostMapping("/crear")
    public ResponseEntity <String> Crear(@Valid @RequestBody ListaDeseosDTO dto) {
        return ResponseEntity.status(201).body(s.Guardar(dto));
    }

    @PostMapping("/actualizar/{id}")
    public ResponseEntity <String> Actualizar(@PathVariable Integer id, @Valid @RequestBody ListaDeseosDTO dto) {
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