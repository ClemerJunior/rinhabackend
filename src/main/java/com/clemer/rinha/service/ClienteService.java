package com.clemer.rinha.service;

import com.clemer.rinha.domain.Cliente;
import com.clemer.rinha.domain.dto.TransacaoRequestDTO;
import com.clemer.rinha.domain.dto.TransacaoResponseDTO;
import com.clemer.rinha.repositories.ClientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClientRepository clientRepository;

    @Transactional
    public TransacaoResponseDTO efetuarTransacao(Long id, TransacaoRequestDTO dto) throws Exception {
        Optional<Cliente> clienteOptional = clientRepository.findById(id);

        if(clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            Long saldo = calcularSaldo(dto, cliente);
            cliente.setSaldo(saldo);
            clientRepository.save(cliente);
            return new TransacaoResponseDTO(cliente.getLimite(), saldo);
        } else {
            throw new Exception("Essa porra não existe");
        }

    }

    public Long calcularSaldo(TransacaoRequestDTO dto, Cliente cliente) throws Exception {
        String tipoTransacao = dto.getTipo();
        Long valorTransacao = dto.getValor();
        Long saldo = cliente.getSaldo();
        Long limite = cliente.getLimite();

        if(tipoTransacao.equalsIgnoreCase("c")) {
            return saldo + valorTransacao;
        } else {
            if(saldo - valorTransacao < (limite * -1)) {
                throw new Exception("transacao invalida");
            } else {
                return saldo - valorTransacao;
            }
        }
    }
}
