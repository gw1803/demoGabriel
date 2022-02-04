
package com.gabriel1803.demojpa.repository;
import com.gabriel1803.demojpa.domain.QuestaoSorteada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QuestaoSorteadaRepository extends JpaRepository<QuestaoSorteada, Long> {

}