package com.clemer.rinha.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TransacaoDTO {

    private Long valor;
    private String tipo;
    private String descricao;
    private LocalDateTime realizada_em;
}
