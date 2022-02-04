package com.gabriel1803.demojpa.service;

import com.gabriel1803.demojpa.domain.Peca;
import com.gabriel1803.demojpa.repository.PecaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PecaService { 

    private final Logger log = LoggerFactory.getLogger(PecaService.class);

    private final PecaRepository pecaRepository;

    public PecaService(PecaRepository pecaRepository) {
        this.pecaRepository = pecaRepository;
    }

    public List<Peca> findAllList(){
        log.debug("Request to get All Peca");
        return pecaRepository.findAll();
    }

    public Optional<Peca> findOne(Long id) {
        log.debug("Request to get Peca : {}", id);
        return pecaRepository.findById(id);
    }

    public void delete(Long id) {
        log.debug("Request to delete Peca : {}", id);
        pecaRepository.deleteById(id);
    }

    public Peca save(Peca peca) {
        log.debug("Request to save Peca : {}", peca);
        peca = pecaRepository.save(peca);
        return peca;
    }

    public List<Peca> saveAll(List<Peca> pecas) {
        log.debug("Request to save Peca : {}", pecas);
        pecas = pecaRepository.saveAll(pecas);
        return pecas;
    }
}
