package com.clemer.rinha.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Transacao {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "cliente_id")
    private Long clienteId;
    private String tipo;
    private Long valor;
    private String descricao;
    @Column(name = "realizada_em")
    private LocalDateTime realizadaEm;

}
