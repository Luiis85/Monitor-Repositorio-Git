package com.mackenzie.repomonitor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "registro_consulta")
public class RegistroConsulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime realizadaEm;
    private String resultado;
    private String enderecoConsultado;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private DevUser usuario;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getRealizadaEm() { return realizadaEm; }
    public void setRealizadaEm(LocalDateTime realizadaEm) { this.realizadaEm = realizadaEm; }
    public String getResultado() { return resultado; }
    public void setResultado(String resultado) { this.resultado = resultado; }
    public String getEnderecoConsultado() { return enderecoConsultado; }
    public void setEnderecoConsultado(String enderecoConsultado) { this.enderecoConsultado = enderecoConsultado; }
    public DevUser getUsuario() { return usuario; }
    public void setUsuario(DevUser usuario) { this.usuario = usuario; }
}