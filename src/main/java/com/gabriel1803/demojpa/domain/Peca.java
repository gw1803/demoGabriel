package com.gabriel1803.demojpa.domain;
import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "table_peca")

public class Peca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long idPeca;
    @Column(name = "sentido", length = 45,nullable= false)
     private String sentido;

     private String nomePeca;

    private int idRoteiro; 
    public static Peca parseNote(String line) {
        String[] text = line.split(",");
        Peca note = new Peca();
        note.setIdPeca(Long.parseLong(text[0]));
        return note;
    }
    
}
