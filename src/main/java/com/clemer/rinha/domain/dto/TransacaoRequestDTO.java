package com.clemer.rinha.domain.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

@Getter
@Setter
public class TransacaoRequestDTO {

    private String valor;
    private String tipo;
    private String descricao;
}
