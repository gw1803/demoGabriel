package com.gabriel1803.demojpa.repository;

import com.gabriel1803.demojpa.domain.Peca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PecaRepository extends JpaRepository<Peca, Long> {

}
