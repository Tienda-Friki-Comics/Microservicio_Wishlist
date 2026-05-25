package com.tiendafriki.lista_deseos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tiendafriki.lista_deseos.model.Detalle;
import java.util.List;

@Repository
public interface DetalleRepo extends JpaRepository <Detalle, Integer> {

    List <Detalle> findByWishlistID(Integer wishlistId);
    List <Detalle> findByProductoID(Integer productoId);

}