package com.tiendafriki.lista_deseos.service;

import org.springframework.beans.factory.annotation.*;
import com.tiendafriki.lista_deseos.repository.*;
import org.springframework.stereotype.Service;
import com.tiendafriki.lista_deseos.model.*;
import com.tiendafriki.lista_deseos.dto.*;
import java.time.*;
import java.util.*;

@Service
public class ListaDeseosServ {

    @Autowired
    private ListaDeseosRepo ldr;

    public List <ListaDeseos> listar() {
        return ldr.findAll();
    }

    public Optional <ListaDeseos> buscarxID(Integer id) {
        return ldr.findByID(id);
    }

    public Optional <ListaDeseos> buscarxRut(String rut) {
        return ldr.findByRut(rut);
    }

    public String Guardar(ListaDeseosDTO dto) {
        Optional <ListaDeseos> Existente = ldr.findByRut(dto.getRut());
        if (!Existente.isEmpty()) {
            return "[+] Ya Existe Una Wishlist Para El Cliente " + dto.getRut() + " [>_<] ... ";
        }
        ListaDeseos ld = new ListaDeseos();
        ld.setRut(dto.getRut());
        ld.setFecha(LocalDateTime.now());
        ldr.save(ld);
        return "[+] Wishlist Creada Correctamente Para " + dto.getRut() + " [>_<] ... ";
    }

    public String Actualizar(Integer id, ListaDeseosDTO dto) {
        Optional <ListaDeseos> ld = ldr.findByID(id);
        if (ld.isPresent()) {
            ListaDeseos l = ld.get();
            l.setRut(dto.getRut());
            ldr.save(l);
            return "[+] Wishlist Actualizada Correctamente [>_<] ... ";
        }
        return "[+] Wishlist Con El ID : " + id + " No Encontrada [>_<] ... ";
    }

    public String Eliminar(Integer id) {
        Optional <ListaDeseos> ld = ldr.findByID(id);
        if (ld.isPresent()) {
            ldr.deleteById(id);
            return "[+] Wishlist Eliminada Correctamente [>_<] ... ";
        }
        return "[+] Wishlist Con El ID : " + id + " No a Sido Encontrada [>_<] ... ";
    }

}