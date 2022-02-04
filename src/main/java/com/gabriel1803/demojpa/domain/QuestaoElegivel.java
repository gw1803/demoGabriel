package com.gabriel1803.demojpa.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "table_questao_elegivel")

public class QuestaoElegivel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idQuestaoElegivel;
    
    @Column(name = "numeroParteFixo")
    private int numeroPartefixo;
    
    @Column(name = "tipoConhecimento", nullable = false)
    private int tipoConhecimento;

    @Column(name = "sentidoIdentificacao", nullable = false)
    private int sentidoIdentificacao;

    @ManyToOne
    private GrupoQuestao grupoQuestaos;

    public static QuestaoElegivel parseNote(String line) {
        String[] text = line.split(",");
        QuestaoElegivel note = new QuestaoElegivel();
        note.setIdQuestaoElegivel(Long.parseLong(text[0]));
        return note;
    }
}
