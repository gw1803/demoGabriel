package com.gabriel1803.demojpa.web;

import com.gabriel1803.demojpa.domain.GrupoQuestao;
import com.gabriel1803.demojpa.service.GrupoQuestaoService;
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
@RequestMapping("/grupoQuestaos")
@CrossOrigin(origins = "*")
public class GrupoQuestaoResource {
    private final Logger log = LoggerFactory.getLogger(GrupoQuestaoResource.class);

    private final GrupoQuestaoService grupoQuestaoService;

    public GrupoQuestaoResource(GrupoQuestaoService grupoQuestaoService) {
        this.grupoQuestaoService = grupoQuestaoService;
    }

    /**
     * {@code GET  /avaliacoes/:id} : get the "id" grupoQuestao.
     *
     * @param id o id da grupoQuestao que será buscada.
     * @return o {@link ResponseEntity} com status {@code 200 (OK)} e no body a grupoQuestao, ou com status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<GrupoQuestao> getGrupoQuestao(@PathVariable Long id) {
        log.debug("REST request to get GrupoQuestao : {}", id);
        Optional<GrupoQuestao> grupoQuestao = grupoQuestaoService.findOne(id);
        if(grupoQuestao.isPresent()) {
            return ResponseEntity.ok().body(grupoQuestao.get());
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/")
    public ResponseEntity<List<GrupoQuestao>> getGrupoQuestaos(){
        List<GrupoQuestao> lista = grupoQuestaoService.findAllList();
        if(lista.size() > 0) {
            return ResponseEntity.ok().body(lista);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * {@code PUT  /avaliacoes} : Atualiza uma grupoQuestao existenteUpdate.
     *
     * @param grupoQuestao a grupoQuestao a ser atulizada.
     * @return o {@link ResponseEntity} com status {@code 200 (OK)} e no corpo a grupoQuestao atualizada,
     * ou com status {@code 400 (Bad Request)} se a grupoQuestao não é válido,
     * ou com status {@code 500 (Internal Server Error)} se a grupoQuestao não pode ser atualizada.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/")
    public ResponseEntity<GrupoQuestao> updateGrupoQuestao(@RequestBody GrupoQuestao grupoQuestao) throws URISyntaxException {
        log.debug("REST request to update GrupoQuestao : {}", grupoQuestao);
        if (grupoQuestao.getIdGrupoQuestao() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid GrupoQuestao id null");
        }
        GrupoQuestao result = grupoQuestaoService.save(grupoQuestao);
        return ResponseEntity.ok()
                .body(result);
    }

    /**
     * {@code POST  /} : Create a new grupoQuestao.
     *
     * @param grupoQuestao the grupoQuestao to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new grupoQuestao, or with status {@code 400 (Bad Request)} if the grupoQuestao has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/")
    public ResponseEntity<GrupoQuestao> createGrupoQuestao(@RequestBody GrupoQuestao grupoQuestao) throws URISyntaxException {
        log.debug("REST request to save GrupoQuestao : {}", grupoQuestao);
        if (grupoQuestao.getIdGrupoQuestao() != null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Um novo grupoQuestao não pode terum ID");
        }
        GrupoQuestao result = grupoQuestaoService.save(grupoQuestao);
        return ResponseEntity.created(new URI("/api/grupoQuestaos/" + result.getIdGrupoQuestao()))
                .body(result);
    }

    /**
     * {@code DELETE  /:id} : delete pelo "id" grupoQuestao.
     *
     * @param id o id da grupoQuestaos que será delete.
     * @return o {@link ResponseEntity} com status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrupoQuestao(@PathVariable Long id) {
        log.debug("REST request to delete GrupoQuestao : {}", id);

        grupoQuestaoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
