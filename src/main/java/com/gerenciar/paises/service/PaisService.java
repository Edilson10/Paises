package com.gerenciar.paises.service;

import com.gerenciar.paises.model.Pais;

import java.util.List;

public interface PaisService {
    Pais savePais(Pais pais);
    List<Pais> getAllPaises();
    List<Pais> getByName(String regiao);
    Pais getPaisById(long id);
    Pais updatePais(Pais pais, long id);
    void deletePais(long id);
}
