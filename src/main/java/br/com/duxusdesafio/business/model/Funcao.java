package br.com.duxusdesafio.business.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Funcao {
    @Id
    @GeneratedValue
    private Long id;
    private String nome;
}
