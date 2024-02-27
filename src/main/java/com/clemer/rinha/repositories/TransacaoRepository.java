package com.clemer.rinha.repositories;

import com.clemer.rinha.domain.Transacao;
import com.clemer.rinha.domain.dto.TransacaoDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    Optional<List<Transacao>> findAllByClienteIdOrderByRealizadaEmDesc(Long cliente_id);
}
