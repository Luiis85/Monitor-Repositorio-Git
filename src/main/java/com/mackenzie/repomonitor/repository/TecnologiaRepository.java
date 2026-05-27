package com.mackenzie.repomonitor.repository;

import java.util.Optional;
import com.mackenzie.repomonitor.model.Tecnologia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TecnologiaRepository extends JpaRepository<Tecnologia, Long> {

    Optional<Tecnologia> findByNome(String nome);

    // Busca tecnologias ordenadas pela quantidade de projetos que as usam
    @Query("SELECT t.nome, COUNT(p) FROM Tecnologia t JOIN t.projetos p GROUP BY t.nome ORDER BY COUNT(p) DESC")
    List<Object[]> rankingPorQuantidadeDeProjetos();
}