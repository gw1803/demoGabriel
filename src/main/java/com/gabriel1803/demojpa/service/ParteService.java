package com.gabriel1803.demojpa.service;

import com.gabriel1803.demojpa.domain.Parte;
import com.gabriel1803.demojpa.repository.ParteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParteService { 

    private final Logger log = LoggerFactory.getLogger(ParteService.class);

    private final ParteRepository parteRepository;

    public ParteService(ParteRepository parteRepository) {
        this.parteRepository = parteRepository;
    }

    public List<Parte> findAllList(){
        log.debug("Request to get All Parte");
        return parteRepository.findAll();
    }

    public Optional<Parte> findOne(Long id) {
        log.debug("Request to get Parte : {}", id);
        return parteRepository.findById(id);
    }

    public void delete(Long id) {
        log.debug("Request to delete Parte : {}", id);
        parteRepository.deleteById(id);
    }

    public Parte save(Parte parte) {
        log.debug("Request to save Parte : {}", parte);
        parte = parteRepository.save(parte);
        return parte;
    }

    public List<Parte> saveAll(List<Parte> partes) {
        log.debug("Request to save Parte : {}", partes);
        partes = parteRepository.saveAll(partes);
        return partes;
    }
}
