package br.com.duxusdesafio.business.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Integrante {
    @Id
    @GeneratedValue
    @Column(name = "integrante_id")
    private Long id;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "franquia_id")
//    private Franquia franquia;
    private String nome;
//    private Funcao funcao;
}
