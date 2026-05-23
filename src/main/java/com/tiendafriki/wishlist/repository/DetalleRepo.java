package com.tiendafriki.wishlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tiendafriki.wishlist.model.Item;
import java.util.List;

@Repository
public interface DetalleRepo JpaRepository <Detalle, Integer> {

    List <Detalle> findByWishlistID(Integer wishlistId);
    List <Detalle> findByProductoID(Integer productoId);

}