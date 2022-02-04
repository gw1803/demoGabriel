package com.gabriel1803.demojpa.service;

import com.gabriel1803.demojpa.domain.QuestaoElegivel;
import com.gabriel1803.demojpa.repository.QuestaoElegivelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestaoElegivelService {

    private final Logger log = LoggerFactory.getLogger(ProfessorService.class);

    private final QuestaoElegivelRepository questaoElegivelRepository;

    public QuestaoElegivelService(QuestaoElegivelRepository questaoElegivelRepository) {
        this.questaoElegivelRepository = questaoElegivelRepository;
    }

    public List<QuestaoElegivel> findAllList(){
        log.debug("Request to get All QuestaoElegivel");
        return questaoElegivelRepository.findAll();
    }

    public Optional<QuestaoElegivel> findOne(Long id) {
        log.debug("Request to get QuestaoElegivel : {}", id);
        return questaoElegivelRepository.findById(id);
    }

    public void delete(Long id) {
        log.debug("Request to delete QuestaoElegivel : {}", id);
        questaoElegivelRepository.deleteById(id);
    }

    public QuestaoElegivel save(QuestaoElegivel questaoElegivel) {
        log.debug("Request to save QuestaoElegivel : {}", questaoElegivel);
        questaoElegivel = questaoElegivelRepository.save(questaoElegivel);
        return questaoElegivel;
    }

    public List<QuestaoElegivel> saveAll(List<QuestaoElegivel> questaoElegivels) {
        log.debug("Request to save QuestaoElegivel : {}", questaoElegivels);
        questaoElegivels = questaoElegivelRepository.saveAll(questaoElegivels);
        return questaoElegivels;
    }
}
