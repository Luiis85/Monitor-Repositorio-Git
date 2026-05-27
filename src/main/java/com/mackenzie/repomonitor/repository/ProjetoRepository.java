package com.mackenzie.repomonitor.repository;

import com.mackenzie.repomonitor.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

    List<Projeto> findByDonoId(Long idDoUsuario);

    List<Projeto> findByLinguagemPrincipal(String linguagem);

    List<Projeto> findByDonoLogin(String login);
}