package com.mackenzie.repomonitor.repository;

import com.mackenzie.repomonitor.model.RegistroConsulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<RegistroConsulta, Long> {
    List<RegistroConsulta> findByUsuarioId(Long idDoUsuario);
}
