package com.gabriel1803.demojpa.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "table_avaliacao")
public class Avaliacao {

    @ManyToMany
    @JoinTable(name="table_Avaliacao_has_Roteiro", joinColumns=
    {@JoinColumn(name="idAvaliacao")}, inverseJoinColumns=
    {@JoinColumn(name="idRoteiro")})
    private List<Roteiro> roteiros;

    @OneToMany
    @JoinColumn(name="id_Avaliacao")
    private List<Avaliacao_has_Questao> questoes;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", length = 64, nullable = false)
    private String titulo;

    private String subtitulo;

    @Column(nullable = false)
    private String nomeProfessor;

    @Column(nullable = false)
    private double valorTotal;

    @Column(nullable = false)
    private double notaTotal;

    @Column(nullable = false)
    private TimeUnit  tempo;

    @Column(nullable = false)
    private String instrucoes;

    @Column(nullable = false)
    private int numeroTentativas;
}
