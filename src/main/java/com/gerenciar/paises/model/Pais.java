package com.gerenciar.paises.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "paises")
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Nome é de preenchimento obrigatório")
    @Column(name = "nome")
    private String nome;

    @NotBlank(message = "Capital é de preenchimento obrigatório")
    @Column(name = "capital")
    private String capital;

    @NotBlank(message = "Regiao é de preenchimento obrigatório")
    @Column(name = "regiao")
    private String regiao;

    @NotBlank(message = "Subregiao é de preenchimento obrigatório")
    @Column(name = "subregiao")
    private String subregiao;

    @NotNull(message = "Area é de preenchimento obrigatório")
    @Column(name = "area")
    private Integer area;
}
