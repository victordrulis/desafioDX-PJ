package br.com.duxusdesafio.business.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Time {
    @Id
    @GeneratedValue
    private Long id;

    private String descricao;

    @Column(nullable = false)
    private LocalDate data;

    @OneToOne
    private Franquia franquia;

    @OneToMany(mappedBy = "time")
    private Set<ComposicaoTime> composicaoTime;

}
