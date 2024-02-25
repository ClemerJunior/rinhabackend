package com.clemer.rinha.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SaldoDTO {

    private Long total;
    private LocalDateTime data_extrato;
    private Long limite;
}
