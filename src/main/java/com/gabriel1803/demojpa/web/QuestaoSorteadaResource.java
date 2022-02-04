package com.gabriel1803.demojpa.web;

import com.gabriel1803.demojpa.domain.QuestaoSorteada;
import com.gabriel1803.demojpa.service.QuestaoSorteadaService;
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
@RequestMapping("/questaoSorteadas")
@CrossOrigin(origins = "*")
public class QuestaoSorteadaResource {
    private final Logger log = LoggerFactory.getLogger(QuestaoSorteadaResource.class);

    private final QuestaoSorteadaService questaoSorteadaService;

    public QuestaoSorteadaResource(QuestaoSorteadaService questaoSorteadaService) {
        this.questaoSorteadaService = questaoSorteadaService;
    }

    /**
     * {@code GET  /questaoSorteadas/:id} : get the "id" questao.
     *
     * @param id o id da questaoSorteada que será buscada.
     * @return o {@link ResponseEntity} com status {@code 200 (OK)} e no body a questao, ou com status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<QuestaoSorteada> getQuestaoSorteada(@PathVariable Long id) {
        log.debug("REST request to get questaoSorteada : {}", id);
        Optional<QuestaoSorteada> questaoSorteada = questaoSorteadaService.findOne(id);
        if(questaoSorteada.isPresent()) {
            return ResponseEntity.ok().body(questaoSorteada.get());
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/")
    public ResponseEntity<List<QuestaoSorteada>> getQuestaoSorteadas(){
        List<QuestaoSorteada> lista = questaoSorteadaService.findAllList();
        if(lista.size() > 0) {
            return ResponseEntity.ok().body(lista);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * {@code PUT  /questaoSorteadas} : Atualiza uma avaliacao existenteUpdate.
     *
     * @param questaoSorteada a questaoSorteada a ser atulizada.
     * @return o {@link ResponseEntity} com status {@code 200 (OK)} e no corpo a questaoSorteada atualizada,
     * ou com status {@code 400 (Bad Request)} se a questaoSorteada não é válido,
     * ou com status {@code 500 (Internal Server Error)} se a questaoSorteada não pode ser atualizada.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/")
    public ResponseEntity<QuestaoSorteada> updateAvaliacao(@RequestBody QuestaoSorteada questaoSorteada) throws URISyntaxException {
        log.debug("REST request to update Avaliacao : {}", questaoSorteada);
        if (questaoSorteada.getIdQuestaoSorteada() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid questaoSorteada id null");
        }
        QuestaoSorteada result = questaoSorteadaService.save(questaoSorteada);
        return ResponseEntity.ok()
                .body(result);
    }

    /**
     * {@code POST  /} : Create a new questaoSorteada.
     *
     * @param questaoSorteada the questaoSorteada to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new questaoSorteada, or with status {@code 400 (Bad Request)} if the questaoSorteada has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/")
    public ResponseEntity<QuestaoSorteada> createQuestaoSorteada(@RequestBody QuestaoSorteada questaoSorteada) throws URISyntaxException {
        log.debug("REST request to save questaoSorteada : {}", questaoSorteada);
        if (questaoSorteada.getIdQuestaoSorteada() != null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Um novo questaoSorteada não pode terum ID");
        }
        QuestaoSorteada result = questaoSorteadaService.save(questaoSorteada);
        return ResponseEntity.created(new URI("/api/questaoSorteadas/" + result.getIdQuestaoSorteada()))
                .body(result);
    }

    /**
     * {@code DELETE  /:id} : delete pelo "id" questaoSorteada.
     *
     * @param id o id da questaoSorteadas que será delete.
     * @return o {@link ResponseEntity} com status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestaoSorteada(@PathVariable Long id) {
        log.debug("REST request to delete questaoSorteada : {}", id);

        questaoSorteadaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
