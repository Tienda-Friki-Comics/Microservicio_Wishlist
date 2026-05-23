package com.tiendafriki.wishlist.service;

import org.springframework.beans.factory.annotation.*;
import com.tiendafriki.wishlist.repository.WishlistRepo;
import org.springframework.stereotype.Service;
import com.tiendafriki.wishlist.dto.WishlistDTO;
import com.tiendafriki.wishlist.model.Wishlist;
import java.time.*;
import java.util.*;

@Service
public class WishlistServ {

    @Autowired
    private WishlistRepo wr;

    public List <Wishlist> listar() {
        return wr.findAll();
    }

    public Optional <Wishlist> buscarxID(Integer id) {
        return wr.findByID(id);
    }

    public List <Wishlist> buscarxNombre(String nombre) {
        return wr.findByNombreIgnoreCase(nombre);
    }

    public String Guardar(WishlistDTO dto) {
        List <Wishlist> Existente = wr.findByNombreIgnoreCase(dto.getNombre());
        if (!Existente.isEmpty()) {
            return "[+] Ya Existe Una Wishlist Para El Cliente " + dto.getNombre() + " [>_<] ... ";
        }
        Wishlist w = new Wishlist();
        w.setNombre(dto.getNombre());
        w.setFecha(LocalDateTime.now());
        wr.save(w);
        return "[+] Wishlist Creada Correctamente Para " + dto.getNombre() + " [>_<] ... ";
    }

    public String Actualizar(Integer id, WishlistDTO dto) {
        Optional <Wishlist> wt = wr.findByID(id);
        if (wt.isPresent()) {
            Wishlist w = wt.get();
            w.setNombre(dto.getNombre());
            wr.save(w);
            return "[+] Wishlist Actualizada Correctamente [>_<] ... ";
        }
        return "[+] Wishlist Con El ID : " + id + " No Encontrada [>_<] ... ";
    }

    public String Eliminar(Integer id) {
        Optional <Wishlist> wt = wr.findByID(id);
        if (wt.isPresent()) {
            wr.deleteById(id);
            return "[+] Wishlist Eliminada Correctamente [>_<] ... ";
        }
        return "[+] Wishlist Con El ID : " + id + " No a Sido Encontrada [>_<] ... ";
    }

}