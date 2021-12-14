package com.gabriel1803.demojpa.domain;

import java.util.List;
import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "table_pecas")

public class Pecas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long idPecas;
    @Column(name = "sentido", length = 45,nullable= false)
     private String sentido;
    
    @ManyToOne
    private Roteiro Roteiro;
    
   @OneToMany(mappedBy = "Pecas")
    private List<Parte> partes;

   
    public static Pecas parseNote(String line) {
        String[] text = line.split(",");
        Pecas note = new Pecas();
        note.setIdPecas(Long.parseLong(text[0]));
        return note;
    }
    
}
