package com.clemer.rinha.controller;

import com.clemer.rinha.domain.dto.TransacaoRequestDTO;
import com.clemer.rinha.domain.dto.TransacaoResponseDTO;
import com.clemer.rinha.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping("/clientes/{id}/transacoes")
    public ResponseEntity<TransacaoResponseDTO> efetuarTransacao(
            @PathVariable Long id,
            @RequestBody TransacaoRequestDTO requestDTO) throws Exception {

        return ResponseEntity.ok(clienteService.efetuarTransacao(id, requestDTO));
    }
}
