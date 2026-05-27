package com.mackenzie.repomonitor.repository;

import com.mackenzie.repomonitor.model.DevUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/*
 * Interface de acesso ao banco para a tabela "dev_user".
 *
 * O Spring Data JPA gera todo o código SQL automaticamente.
 * Não precisamos escrever nenhuma query na mão - basta declarar
 * o método com o nome certo e o Spring faz o resto.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<DevUser, Long> {

    // "findByLogin" → gera: SELECT * FROM dev_user WHERE login = ?
    Optional<DevUser> findByLogin(String login);

    // Verifica existência sem precisar carregar o objeto inteiro
    boolean existsByLogin(String login);
}
