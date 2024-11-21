package br.com.duxusdesafio.business.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Time {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    @Column(nullable = false)
    private LocalDate data;

    @OneToOne
    private Franquia franquia;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "time", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Singular
    private Set<ComposicaoTime> composicaoTimes;

}
