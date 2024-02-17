package com.clemer.rinha.service;

import com.clemer.rinha.domain.dto.TransacaoRequestDTO;
import com.clemer.rinha.domain.dto.TransacaoResponseDTO;
import com.clemer.rinha.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClientRepository clientRepository;

    public TransacaoResponseDTO efetuarTransacao(TransacaoRequestDTO dto) {
        TransacaoResponseDTO transacaoResponseDTO = new TransacaoResponseDTO();
        clientRepository.findById(dto.getId()).ifPresent(cliente -> {
            if (dto.getTipo().equals("CREDITO")) {
                transacaoResponseDTO.setSaldo(verifySaldo(cliente.getSaldo() + dto.getValor(), cliente.getLimite()));
                transacaoResponseDTO.setLimite(cliente.getLimite());
            } else {
                transacaoResponseDTO.setSaldo(verifySaldo(cliente.getSaldo() - dto.getValor(), cliente.getLimite()));
                transacaoResponseDTO.setLimite(cliente.getLimite());
            }
            clientRepository.save(cliente);
        });

        return transacaoResponseDTO;
    }

    private Long verifySaldo(Long result, Long limit) {
        limit = -Math.abs(limit);
        if (result > limit) {
            throw new RuntimeException("Saldo insuficiente");
        }
        return result;
    }
}
