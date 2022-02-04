package com.gabriel1803.demojpa.service;

import com.gabriel1803.demojpa.domain.QuestaoSorteada;
import com.gabriel1803.demojpa.repository.QuestaoSorteadaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestaoSorteadaService {

    private final Logger log = LoggerFactory.getLogger(QuestaoSorteadaService.class);

    private final QuestaoSorteadaRepository questaoRepository;

    public QuestaoSorteadaService(QuestaoSorteadaRepository questaoRepository) {
        this.questaoRepository = questaoRepository;
    }

    public List<QuestaoSorteada> findAllList(){
        log.debug("Request to get All Questao");
        return questaoRepository.findAll();
    }

    public Optional<QuestaoSorteada> findOne(Long id) {
        log.debug("Request to get Questao : {}", id);
        return questaoRepository.findById(id);
    }

    public void delete(Long id) {
        log.debug("Request to delete Questao : {}", id);
        questaoRepository.deleteById(id);
    }

    public QuestaoSorteada save(QuestaoSorteada questao) {
        log.debug("Request to save Questao : {}", questao);
        questao = questaoRepository.save(questao);
        return questao;
    }

    public List<QuestaoSorteada> saveAll(List<QuestaoSorteada> questoes) {
        log.debug("Request to save Questao : {}", questoes);
        questoes = questaoRepository.saveAll(questoes);
        return questoes;
    }
}
