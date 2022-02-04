package com.gabriel1803.demojpa.domain;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "table_roteiro")

public class Roteiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRoteiro;

    @Column(name = "assunto", length = 45, nullable= false)
     private String assunto;
    @Column(name = "conteudo", length = 1000, nullable= false)
     private String conteudo;
    
    public static Roteiro parseNote(String line) {
        String[] text = line.split(",");
        Roteiro note = new Roteiro();
        note.setIdRoteiro(Long.parseLong(text[0]));
        return note;
    }
}



