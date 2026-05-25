package com.tiendafriki.lista_deseos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tiendafriki.lista_deseos.model.ListaDeseos;
import java.util.*;

@Repository
public interface ListaDeseosRepo extends JpaRepository <ListaDeseos, Integer> {

    List <ListaDeseos> findByProductoIgnoreCase(String producto);   
    Optional <ListaDeseos> findByID(Integer id);  
    Optional <ListaDeseos> findByRut(String rut);

}