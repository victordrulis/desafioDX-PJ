package br.com.duxusdesafio.business.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;

import java.util.Set;

@Entity
@Data
public class ComposicaoTime {
    @Id
    @GeneratedValue
    private Long id;

}
