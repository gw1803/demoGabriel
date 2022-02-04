package com.gabriel1803.demojpa.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "table_grupo_questao")

public class GrupoQuestao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 64, nullable = false)
    private Long idGrupoQuestao;
    
    @Column(name = "nomeGrupo", length = 64)
    private String nomeGrupo;

    @Column(name = "pontoPorQuestao", nullable = false)
    private double pontoPorQuestao;

    @Column(name = "quantidadeParaSortear", nullable = false)
    private int quantidadeParaSortear;

    public static GrupoQuestao parseNote(String line) {
        String[] text = line.split(",");
        GrupoQuestao note = new GrupoQuestao();
        note.setIdGrupoQuestao(Long.parseLong(text[0]));
        return note;
    }
}
