package com.gabriel1803.demojpa.web;

import com.gabriel1803.demojpa.domain.Avaliacao;
import com.gabriel1803.demojpa.service.AvaliacaoService;
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
@RequestMapping("/avaliacoes")
@CrossOrigin(origins = "*")
public class AvaliacaoResource {
    private final Logger log = LoggerFactory.getLogger(AvaliacaoResource.class);

    private final AvaliacaoService avaliacaoService;

    public AvaliacaoResource(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    /**
     * {@code GET  /avaliacoes/:id} : get the "id" avaliacao.
     *
     * @param id o id da avaliacao que será buscada.
     * @return o {@link ResponseEntity} com status {@code 200 (OK)} e no body a avaliacao, ou com status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Avaliacao> getAvaliacao(@PathVariable Long id) {
        log.debug("REST request to get Avaliacao : {}", id);
        Optional<Avaliacao> avaliacao = avaliacaoService.findOne(id);
        if(avaliacao.isPresent()) {
            return ResponseEntity.ok().body(avaliacao.get());
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/")
    public ResponseEntity<List<Avaliacao>> getAvaliacoes(){
        List<Avaliacao> lista = avaliacaoService.findAllList();
        if(lista.size() > 0) {
            return ResponseEntity.ok().body(lista);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * {@code PUT  /avaliacoes} : Atualiza uma avaliacao existenteUpdate.
     *
     * @param avaliacao a avaliacao a ser atulizada.
     * @return o {@link ResponseEntity} com status {@code 200 (OK)} e no corpo a avaliacao atualizada,
     * ou com status {@code 400 (Bad Request)} se a avaliacao não é válido,
     * ou com status {@code 500 (Internal Server Error)} se a avaliacao não pode ser atualizada.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/")
    public ResponseEntity<Avaliacao> updateAvaliacao(@RequestBody Avaliacao avaliacao) throws URISyntaxException {
        log.debug("REST request to update Avaliacao : {}", avaliacao);
        if (avaliacao.getIdAvaliacao() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid Avaliacao id null");
        }
        Avaliacao result = avaliacaoService.save(avaliacao);
        return ResponseEntity.ok()
                .body(result);
    }

    /**
     * {@code POST  /} : Create a new avaliacao.
     *
     * @param avaliacao the avaliacao to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new avaliacao, or with status {@code 400 (Bad Request)} if the avaliacao has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/")
    public ResponseEntity<Avaliacao> createAvaliacao(@RequestBody Avaliacao avaliacao) throws URISyntaxException {
        log.debug("REST request to save Avaliacao : {}", avaliacao);
        if (avaliacao.getIdAvaliacao() != null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Um novo avaliacao não pode terum ID");
        }
        Avaliacao result = avaliacaoService.save(avaliacao);
        return ResponseEntity.created(new URI("/api/avaliacoes/" + result.getIdAvaliacao()))
                .body(result);
    }

    /**
     * {@code DELETE  /:id} : delete pelo "id" avaliacao.
     *
     * @param id o id da avaliacoes que será delete.
     * @return o {@link ResponseEntity} com status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvaliacao(@PathVariable Long id) {
        log.debug("REST request to delete Avaliacao : {}", id);

        avaliacaoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
