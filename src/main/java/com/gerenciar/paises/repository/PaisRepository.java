package com.gerenciar.paises.repository;

import com.gerenciar.paises.model.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PaisRepository  extends JpaRepository<Pais, Long> {
    @Query( "SELECT p FROM Pais p WHERE p.regiao = :regiao" )
    List<Pais> findByName(String regiao);
}
