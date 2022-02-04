package com.gabriel1803.demojpa.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "table_questao_sorteada")
public class QuestaoSorteada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idQuestaoSorteada;

    @Column( nullable = false)
    private int numeroParteSorteado;

    private String comentarioProfessor;

    @Column(nullable = false)
    private double notaCorrecao;

    @Column(nullable = false)
    private String tempoGasto;

    private int respostaNumeroParte;

    private String respostaNome;

    private String respostaConhecimento;

    //idQuestaoElegivel
    private Long questaoElegivels;

    public static QuestaoSorteada parseNote(String line) {
        String[] text = line.split(",");
        QuestaoSorteada note = new QuestaoSorteada();
        note.setIdQuestaoSorteada(Long.parseLong(text[0]));
        return note;
    }

}
