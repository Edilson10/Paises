package com.gerenciar.paises.controller;

import com.gerenciar.paises.model.Pais;
import com.gerenciar.paises.service.PaisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pais")
public class PaisController {
    private PaisService paisService;

    public PaisController(PaisService paisService) {
        super();
        this.paisService = paisService;
    }

    //criar Pais Rest Api
    @PostMapping("/criar")
    public ResponseEntity<Pais> savePais(@Valid @RequestBody Pais pais) {
        return new ResponseEntity<Pais>(paisService.savePais(pais), HttpStatus.CREATED);
    }

    //retornar  Paises Rest Api
    @GetMapping("/listar")
    public List<Pais> getAllPaises() {
        return paisService.getAllPaises();
    }

    //retorn pais por id Rest Api
    @GetMapping("/listar/{id}")
    public ResponseEntity<Pais> getPaisById(@PathVariable("id") long paisId){
        return new ResponseEntity<Pais>(paisService.getPaisById(paisId), HttpStatus.OK);
    }

    ///ordenar pais por regiao Rest Api
    @GetMapping("/ordenar")
    public ResponseEntity<List<Pais>> getPaisByName(@RequestParam(name = "regiao") String regiao){
        List<Pais> pais = paisService.getByName(regiao);
        return new ResponseEntity<List<Pais>>(pais, HttpStatus.OK);
    }

    //Atualizar pais Rest Api
    @PutMapping("/modificar/{id}")
    public ResponseEntity<Pais> updatePais(@PathVariable("id") long id,
                                           @Valid @RequestBody Pais pais){
        return new ResponseEntity<Pais>(paisService.updatePais(pais, id), HttpStatus.OK);

    }

    //Eliminar pais Rest
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> deletePais(@PathVariable("id") long id){
        //eliminar pais na DB
        paisService.deletePais(id);
        return new ResponseEntity<String>("Pais eliminado com sucesso", HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
