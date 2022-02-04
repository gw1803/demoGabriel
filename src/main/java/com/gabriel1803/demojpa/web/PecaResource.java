package com.gabriel1803.demojpa.web;

import com.gabriel1803.demojpa.domain.Peca;
import com.gabriel1803.demojpa.service.PecaService;
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
@RequestMapping("/pecas")
@CrossOrigin(origins = "*")
public class PecaResource {
    private final Logger log = LoggerFactory.getLogger(Peca.class);

    private final PecaService pecaService;

    public PecaResource(PecaService pecaService) {
        this.pecaService = pecaService;
    }

    /**
     * {@code GET  /pecas/:id} : get the "id" peca.
     *
     * @param id o id da peca que será buscada.
     * @return o {@link ResponseEntity} com status {@code 200 (OK)} e no body a peca, ou com status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Peca> getPeca(@PathVariable Long id)
    {
        log.debug("REST request to get Peca : {}", id);
        Optional<Peca> peca = pecaService.findOne(id);
        if(peca.isPresent()) {
            return ResponseEntity.ok().body(peca.get());
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/")
    public ResponseEntity<List<Peca>> getPeca(){
        List<Peca> lista = pecaService.findAllList();
        if(lista.size() > 0) {
            return ResponseEntity.ok().body(lista);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * {@code PUT  /pecas} : Atualiza uma grupoQuestao existenteUpdate.
     *
     * @param grupoQuestao a grupoQuestao a ser atulizada.
     * @return o {@link ResponseEntity} com status {@code 200 (OK)} e no corpo a grupoQuestao atualizada,
     * ou com status {@code 400 (Bad Request)} se a grupoQuestao não é válido,
     * ou com status {@code 500 (Internal Server Error)} se a grupoQuestao não pode ser atualizada.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/")
    public ResponseEntity<Peca> updatePeca(@RequestBody Peca peca) throws URISyntaxException {
        log.debug("REST request to update Peca : {}", peca);
        if (peca.getIdPeca() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid peca id null");
        }
        Peca result = pecaService.save(peca);
        return ResponseEntity.ok()
                .body(result);
    }

    /**
     * {@code POST  /} : Create a new peca.
     *
     * @param peca the peca to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new peca, or with status {@code 400 (Bad Request)} if the peca has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/")
    public ResponseEntity<Peca> createPeca(@RequestBody Peca peca) throws URISyntaxException {
        log.debug("REST request to save Peca : {}", peca);
        if (peca.getIdPeca() != null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Um novo Peca não pode terum ID");
        }
        Peca result = pecaService.save(peca);
        return ResponseEntity.created(new URI("/api/pecas/" + result.getIdPeca()))
                .body(result);
    }

    /**
     * {@code DELETE  /:id} : delete pelo "id" peca.
     *
     * @param id o id da pecas que será delete.
     * @return o {@link ResponseEntity} com status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePeca(@PathVariable Long id) {
        log.debug("REST request to delete Peca : {}", id);

        pecaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}