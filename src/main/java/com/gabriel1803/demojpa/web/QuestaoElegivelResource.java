package com.gabriel1803.demojpa.web;

import com.gabriel1803.demojpa.domain.QuestaoElegivel;
import com.gabriel1803.demojpa.service.QuestaoElegivelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/questaoElegivels")
@CrossOrigin(origins = "*")

public class QuestaoElegivelResource {
    private final Logger log = LoggerFactory.getLogger(QuestaoElegivelResource.class);

    private final QuestaoElegivelService questaoElegivelService;

    public QuestaoElegivelResource(QuestaoElegivelService questaoElegivelService) {
        this.questaoElegivelService = questaoElegivelService;
    }

   
    @GetMapping("/{id}")
    public ResponseEntity<QuestaoElegivel> getQuestaoElegivel(@PathVariable Long id) {
        log.debug("REST request to get QuestaoElegivel : {}", id);
        Optional<QuestaoElegivel> questaoElegivel = questaoElegivelService.findOne(id);
        if(questaoElegivel.isPresent()) {
            return ResponseEntity.ok().body(questaoElegivel.get());
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/")
    public ResponseEntity<List<QuestaoElegivel>> getquestaoElegivel(){
        List<QuestaoElegivel> lista = questaoElegivelService.findAllList();
        if(lista.size() > 0) {
            return ResponseEntity.ok().body(lista);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * {@code PUT  /questaoElegivels} : Atualiza uma questaoElegivel existenteUpdate.
     *
     * @param avaliacao a questaoElegivel a ser atulizada.
     * @return o {@link ResponseEntity} com status {@code 200 (OK)} e no corpo a questaoElegivel atualizada,
     * ou com status {@code 400 (Bad Request)} se a questaoElegivel não é válido,
     * ou com status {@code 500 (Internal Server Error)} se a questaoElegivel não pode ser atualizada.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/")
    public ResponseEntity<QuestaoElegivel> updatequestaoElegivel(@RequestBody QuestaoElegivel questaoElegivel) throws URISyntaxException {
        log.debug("REST request to update QuestaoElegivel : {}", questaoElegivel);
        if (questaoElegivel.getIdQuestaoElegivel() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid QuestaoElegivel id null");
        }
        QuestaoElegivel result = questaoElegivelService.save(questaoElegivel);
        return ResponseEntity.ok()
                .body(result);
    }

    /**
     * {@code POST  /} : Create a new questaoElegivel.
     *
     * @param questaoElegivel the questaoElegivel to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new questaoElegivel, or with status {@code 400 (Bad Request)} if the questaoElegivel has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/")
    public ResponseEntity<QuestaoElegivel> createQuestaoElegivel(@RequestBody QuestaoElegivel questaoElegivel) throws URISyntaxException {
        log.debug("REST request to save QuestaoElegivel : {}", questaoElegivel);
        if (questaoElegivel.getIdQuestaoElegivel() != null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Um novo QuestaoElegivel não pode terum ID");
        }
        QuestaoElegivel result = questaoElegivelService.save(questaoElegivel);
        return ResponseEntity.created(new URI("/api/questaoElegivels/" + result.getIdQuestaoElegivel()))
                .body(result);
    }

    /**
     * {@code DELETE  /:id} : delete pelo "id" questaoElegivel.
     *
     * @param id o id da questaoElegivels que será delete.
     * @return o {@link ResponseEntity} com status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestaoElegivel(@PathVariable Long id) {
        log.debug("REST request to delete QuestaoElegivel : {}", id);

        questaoElegivelService.delete(id);
        return ResponseEntity.noContent().build();
    }
}