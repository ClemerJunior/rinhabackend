package com.clemer.rinha.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Cliente {

    @Id
    private Long id;

    @Column(updatable = false)
    private Long limite;

    @PositiveOrZero
    private Long saldo;
}
