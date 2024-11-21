package br.com.duxusdesafio.business.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComposicaoTime {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_time")
    private Time time;

    @ManyToOne
    @JoinColumn(name = "id_integrante")
    private Integrante integrante;

    @Column(nullable = false)
    private LocalDate data;

}
