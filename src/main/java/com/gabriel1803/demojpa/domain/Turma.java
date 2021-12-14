package com.gabriel1803.demojpa.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "table_turma")

public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTurma;
    @Column(name = "nome", length = 64, nullable = false)
    private String nome;
    
    @ManyToOne
    private Professor Professor;
    
    @ManyToMany
    @JoinTable(name="tbTurma_has_tbAluno",
             joinColumns={@JoinColumn(name="id_turma")},
             inverseJoinColumns={@JoinColumn(name="id_alunos")})
    private List<Aluno> alunos;
}
