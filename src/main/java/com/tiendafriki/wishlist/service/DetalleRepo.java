package com.tiendafriki.wishlist.service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import com.tiendafriki.wishlist.repository.*;
import com.tiendafriki.wishlist.model.*;
import com.tiendafriki.wishlist.dto.*;
import java.util.*;

@Service
public class DetalleServ {

    @Autowired
    private DetalleRepo dr;

    @Autowired
    private WishlistRepo wr;

    public List <Detalle> listar() {
        return dr.findAll();
    }

    public Optional <Detalle> buscarxID(Integer id) {
        return dr.findById(id);
    }

    public List <Detalle> buscarxWishlist(Integer wishlistID) {
        return dr.findByWishlistID(wishlistID);
    }

    public List <Detalle> buscarxProducto(Integer productoID) {
        return dr.findByProductoID(productoID);
    }

    public String Guardar(DetalleDTO dto) {
        Optional <Wishlist> wt = wr.findByID(dto.getWishlistID());
        if (wt.isEmpty()) {
            return "[+] La Wishlist Con El ID : " + dto.getWishlistID() + " No Existe [>_<] ... ";
        }

        List <Detalle> Existente = dr.findByWishlistID(dto.getWishlistID());
        boolean YaAgregado = Existente.stream()
            .anyMatch(d -> d.getProductoID().equals(dto.getProductoID()));
        if (YaAgregado) {
            return "[+] El Producto Con El ID : " + dto.getProductoID() + " Ya Esta En La Wishlist [>_<] ... ";
        }

        Detalle detalle = new Detalle();
        detalle.setWishlist(wt.get());
        detalle.setProductoID(dto.getProductoID());
        detalle.setNombreProducto(dto.getNombreProducto());
        detalle.setPrecio(dto.getPrecio());
        dr.save(item);

        return "[+] Detalle Agregado Exitosamente A La Wishlist [>_<] ... " +
               "[+] Producto : " + dto.getNombreProducto() + " | " +
               "[+] Precio : $" + dto.getPrecio() + " [>_<] ... ";
    }

    public String Actualizar(Integer id, DetalleDTO dto) {
        Optional <Detalle> d = dr.findById(id);
        if (d.isEmpty()) {
            return "[+] El Detalle Con El ID : " + id + " No Encontrado [>_<] ... ";
        }

        Detalle detalle = d.get();
        detalle.setProductoID(dto.getProductoID());
        detalle.setNombreProducto(dto.getNombreProducto());
        detalle.setPrecio(dto.getPrecio());
        dr.save(item);

        return "[+] Detalle Actualizado Correctamente. " +
               "[+] Producto : " + dto.getNombreProducto() + " | " +
               "[+] Precio : $" + dto.getPrecio() + " [>_<] ... ";
    }

    public String Eliminar(Integer id) {
        Optional <Detalle> d = dr.findById(id);
        if (d.isPresent()) {
            dr.deleteById(id);
            return "[+] El Detalle a Sido Eliminado Con Exito [>_<] ... ";
        }
        return "[+] El Detalle Con El ID : " + id + " No a Sido Encontrado [>_<] ... ";
    }

}