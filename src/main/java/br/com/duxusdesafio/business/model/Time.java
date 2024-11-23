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
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String descricao;

    @Column(nullable = false)
    private LocalDate data;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "time", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Singular
    private Set<ComposicaoTime> composicaoTimes;

}
