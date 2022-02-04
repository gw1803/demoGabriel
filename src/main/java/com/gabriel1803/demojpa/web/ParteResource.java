package com.gabriel1803.demojpa.web;

import com.gabriel1803.demojpa.domain.Parte;
import com.gabriel1803.demojpa.service.ParteService;
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
@RequestMapping("/partes")
@CrossOrigin(origins = "*")
public class ParteResource {
    private final Logger log = LoggerFactory.getLogger(Parte.class);

    private final ParteService parteService;

    public ParteResource(ParteService parteService) {
        this.parteService = parteService;
    }

    /**
     * {@code GET  /partes/:id} : get the "id" parte.
     *
     * @param id o id da parte que será buscada.
     * @return o {@link ResponseEntity} com status {@code 200 (OK)} e no body a parte, ou com status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Parte> getParte(@PathVariable Long id)
    {
        log.debug("REST request to get Parte : {}", id);
        Optional<Parte> parte = parteService.findOne(id);
        if(parte.isPresent()) {
            return ResponseEntity.ok().body(parte.get());
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/")
    public ResponseEntity<List<Parte>> getParte(){
        List<Parte> lista = parteService.findAllList();
        if(lista.size() > 0) {
            return ResponseEntity.ok().body(lista);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * {@code PUT  /partes} : Atualiza uma grupoQuestao existenteUpdate.
     *
     * @param grupoQuestao a grupoQuestao a ser atulizada.
     * @return o {@link ResponseEntity} com status {@code 200 (OK)} e no corpo a grupoQuestao atualizada,
     * ou com status {@code 400 (Bad Request)} se a grupoQuestao não é válido,
     * ou com status {@code 500 (Internal Server Error)} se a grupoQuestao não pode ser atualizada.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/")
    public ResponseEntity<Parte> updateParte(@RequestBody Parte parte) throws URISyntaxException {
        log.debug("REST request to update Parte : {}", parte);
        if (parte.getIdParte() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid parte id null");
        }
        Parte result = parteService.save(parte);
        return ResponseEntity.ok()
                .body(result);
    }

    /**
     * {@code POST  /} : Create a new parte.
     *
     * @param parte the parte to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new parte, or with status {@code 400 (Bad Request)} if the parte has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/")
    public ResponseEntity<Parte> createParte(@RequestBody Parte parte) throws URISyntaxException {
        log.debug("REST request to save Parte : {}", parte);
        if (parte.getIdParte() != null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Um novo Parte não pode terum ID");
        }
       Parte result = parteService.save(parte);
        return ResponseEntity.created(new URI("/api/partes/" + result.getIdParte()))
                .body(result);
    }

    /**
     * {@code DELETE  /:id} : delete pelo "id" parte.
     *
     * @param id o id da partes que será delete.
     * @return o {@link ResponseEntity} com status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParte(@PathVariable Long id) {
        log.debug("REST request to delete Parte : {}", id);

        parteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}