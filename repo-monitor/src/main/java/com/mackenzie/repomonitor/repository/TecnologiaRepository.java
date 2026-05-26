package com.mackenzie.repomonitor.repository;

import com.mackenzie.repomonitor.model.Tecnologia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TecnologiaRepository extends JpaRepository<Tecnologia, Long> {
    Optional<Tecnologia> findByNome(String nome);
}
