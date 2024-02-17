package com.clemer.rinha.controller;

import com.clemer.rinha.domain.dto.TransacaoRequestDTO;
import com.clemer.rinha.domain.dto.TransacaoResponseDTO;
import com.clemer.rinha.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/clientes/")
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping("{id}/transacoes")
    public ResponseEntity<TransacaoResponseDTO> efetuarTransacao(
            @PathVariable Long id,
            @RequestBody TransacaoRequestDTO requestDTO) {

        return ResponseEntity.ok(clienteService.efetuarTransacao(id, requestDTO));
    }
}

