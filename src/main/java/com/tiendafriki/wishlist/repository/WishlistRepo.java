package com.tiendafriki.wishlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tiendafriki.wishlist.model.Wishlist;
import java.util.*;

@Repository
public interface WishlistRepo extends JpaRepository <Wishlist, Integer> {

    List <Wishlist> findByProductoIgnoreCase(String producto);   
    Optional <Wishlist> findByID(Integer id);

}