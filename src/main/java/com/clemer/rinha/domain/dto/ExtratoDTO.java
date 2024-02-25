package com.clemer.rinha.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExtratoDTO {

    private SaldoDTO saldo;
    private List<TransacaoDTO> ultimas_transacoes;
}
