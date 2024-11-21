package br.com.duxusdesafio.business.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "integrante_id")
    private Long id;

    private String nome;
    @OneToOne
    private Funcao funcao;
    @OneToOne
    private Franquia franquia;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "integrante", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Singular
    private Set<ComposicaoTime> composicaoTimes;

}
