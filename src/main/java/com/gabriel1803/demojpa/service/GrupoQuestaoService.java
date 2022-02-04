package com.gabriel1803.demojpa.service;

import com.gabriel1803.demojpa.domain.GrupoQuestao;
import com.gabriel1803.demojpa.repository.GrupoQuestaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GrupoQuestaoService {

    private final Logger log = LoggerFactory.getLogger(GrupoQuestaoService.class);

    private final GrupoQuestaoRepository grupoQuestaoRepository;

    public GrupoQuestaoService(GrupoQuestaoRepository grupoQuestaoRepository) {
        this.grupoQuestaoRepository = grupoQuestaoRepository;
    }

    public List<GrupoQuestao> findAllList(){
        log.debug("Request to get All GrupoQuestao");
        return grupoQuestaoRepository.findAll();
    }

    public Optional<GrupoQuestao> findOne(Long id) {
        log.debug("Request to get GrupoQuestao : {}", id);
        return grupoQuestaoRepository.findById(id);
    }

    public void delete(Long id) {
        log.debug("Request to delete GrupoQuestao : {}", id);
        grupoQuestaoRepository.deleteById(id);
    }


    public GrupoQuestao save(GrupoQuestao grupoQuestao) {
        log.debug("Request to save GrupoQuestao : {}", grupoQuestao);
        grupoQuestao = grupoQuestaoRepository.save(grupoQuestao);
        return grupoQuestao;
    }

     public List<GrupoQuestao> saveAll(List<GrupoQuestao> GrupoQuestaos) {
        log.debug("Request to save GrupoQuestao : {}", GrupoQuestaos);
        GrupoQuestaos = grupoQuestaoRepository.saveAll(GrupoQuestaos);
        return GrupoQuestaos;
    }
}