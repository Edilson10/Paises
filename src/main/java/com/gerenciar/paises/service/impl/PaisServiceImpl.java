package com.gerenciar.paises.service.impl;

import com.gerenciar.paises.exception.ResourceNotFoundException;
import com.gerenciar.paises.model.Pais;
import com.gerenciar.paises.repository.PaisRepository;
import com.gerenciar.paises.service.PaisService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaisServiceImpl implements PaisService {
    private PaisRepository paisRepository;

    public PaisServiceImpl(PaisRepository paisRepository) {
        super();
        this.paisRepository = paisRepository;
    }

    @Override
    public Pais savePais(Pais pais) {
        return paisRepository.save(pais);
    }

    @Override
    public List<Pais> getAllPaises() {
        return paisRepository.findAll();
    }

    @Override
    public List<Pais> getByName(String regiao) {
        return paisRepository.findByName(regiao);
    }

    @Override
    public Pais getPaisById(long id) {
        return paisRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Pais", "Id", id));
    }

    @Override
    public Pais updatePais(Pais pais, long id) {
        //verificar se existe pais com o id atribuido na database
        Pais existePais = paisRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Pais", "Id", id));

        existePais.setNome(pais.getNome());
        existePais.setCapital(pais.getCapital());
        existePais.setRegiao(pais.getRegiao());
        existePais.setSubregiao(pais.getSubregiao());
        existePais.setArea(pais.getArea());
        //Guardar existe pais na DB
        paisRepository.save (existePais);
        return existePais;
    }

    @Override
    public void deletePais(long id) {
        //verificar se o pais existe na DB ou nao
        paisRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Pais", "Id", id));
        paisRepository.deleteById(id);
    }
}
