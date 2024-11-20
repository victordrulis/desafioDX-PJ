package br.com.duxusdesafio.business.model;

import lombok.*;

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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "integrante", cascade = CascadeType.ALL)
    private Set<ComposicaoTime> composicaoTime;

}
