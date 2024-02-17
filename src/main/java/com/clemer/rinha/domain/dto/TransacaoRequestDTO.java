package com.clemer.rinha.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransacaoRequestDTO {

    @NotNull
    private Long id;
    @NotNull
    private Long valor;
    @NotNull
    private String tipo;
    private String descricao;
}
