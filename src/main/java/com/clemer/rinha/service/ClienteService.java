package com.clemer.rinha.service;

import com.clemer.rinha.domain.Cliente;
import com.clemer.rinha.domain.dto.TransacaoRequestDTO;
import com.clemer.rinha.domain.dto.TransacaoResponseDTO;
import com.clemer.rinha.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClientRepository clientRepository;

    public TransacaoResponseDTO efetuarTransacao(TransacaoRequestDTO dto) {
        Optional<Cliente> cliente = clientRepository.findById(dto.get)

    }
}
