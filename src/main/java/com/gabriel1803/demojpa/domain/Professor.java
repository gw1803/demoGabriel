package com.gabriel1803.demojpa.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "table_professsor")

public class Professor {
    @OneToMany
    @JoinColumn(name="id_Professor")
    private List<Avaliacao> avalicoes;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", length = 64, nullable = false)
    private String nome;
    @Column(name = "dataNascimento", nullable = false)
    private String dataNascimento;
    @Column(nullable = false)
    private String cpf;
    @Column(nullable = false)
    private String telefone;
    @Column(nullable = false)
    private String cep;
    @Column(nullable = false)
    private String rua;
    @Column(nullable = false)
    private String bairro;
    @Column(nullable = false)
    private String cidade;
    private int numero_avaliacoes;
    private int numero_roteiros;
    @Column(nullable = false)
    private String uf;
    

    @OneToMany(mappedBy = "Professor")
    private List<Turma> turmas;
    
    
    public static Professor parseNote(String line) {
        String[] text = line.split(",");
        Professor note = new Professor();
        note.setId(Long.parseLong(text[0]));
        note.setNome(text[1]);
        return note;
    }
}
