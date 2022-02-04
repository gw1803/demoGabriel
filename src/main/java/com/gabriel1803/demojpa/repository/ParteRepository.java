package com.gabriel1803.demojpa.repository;

import com.gabriel1803.demojpa.domain.Parte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParteRepository extends JpaRepository<Parte, Long> {

}
