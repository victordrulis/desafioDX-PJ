package br.com.duxusdesafio.business.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
public class Time {
    @Id
    @GeneratedValue
    private Long id;

    private String descricao;

    @Column(nullable = false)
    private LocalDate data;

}
