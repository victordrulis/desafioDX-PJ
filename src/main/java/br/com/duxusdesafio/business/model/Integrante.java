package br.com.duxusdesafio.business.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Integrante {
    @Id
    @GeneratedValue
    @Column(name = "integrante_id")
    private Long id;

    private String nome;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "integrante")
    private Set<ComposicaoTime> composicaoTime;

}
