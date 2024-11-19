package br.com.duxusdesafio.business.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Funcao {
    @Id
    @GeneratedValue
    private Long id;
}
