package com.mackenzie.repomonitor.repository;

import com.mackenzie.repomonitor.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/*
 * Interface de acesso ao banco para a tabela "projeto".
 */
@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

    // Retorna todos os projetos de um usuário específico
    // → SELECT * FROM projeto WHERE dono_id = ?
    List<Projeto> findByDonoId(Long idDoUsuario);

    // Filtra projetos por linguagem
    // → SELECT * FROM projeto WHERE linguagem_principal = ?
    List<Projeto> findByLinguagemPrincipal(String linguagem);
}
