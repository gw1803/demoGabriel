
package com.gabriel1803.demojpa.domain;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "table_parte")

public class Parte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idParte;

    @Column(name = "conteudo", length = 45, nullable= false)
    private String conteudo;
    @Column(nullable= false)
    private int numero;
    @Column(name = "nome", length = 45,nullable= false)
    private String nome;
    
    private int idPeca;
   
    public static Parte parseNote(String line) {
        String[] text = line.split(",");
        Parte note = new Parte();
        note.setIdParte(Long.parseLong(text[0]));
        return note;
    }
}


