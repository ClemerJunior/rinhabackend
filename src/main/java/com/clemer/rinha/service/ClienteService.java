package com.clemer.rinha.service;

import com.clemer.rinha.domain.Cliente;
import com.clemer.rinha.domain.Transacao;
import com.clemer.rinha.domain.dto.*;
import com.clemer.rinha.exceptions.ClienteInexistenteException;
import com.clemer.rinha.exceptions.LimiteExcedidoException;
import com.clemer.rinha.repositories.ClientRepository;
import com.clemer.rinha.repositories.TransacaoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClientRepository clientRepository;
    private final TransacaoRepository transacaoRepository;

    @Transactional
    public TransacaoResponseDTO efetuarTransacao(Long id, TransacaoRequestDTO dto) {
        validaTranscaoRequest(dto);
        Cliente cliente = buscarCliente(id);

        Long saldo = atualizarSaldo(dto, cliente);
        cliente.setSaldo(saldo);
        clientRepository.save(cliente);

        Transacao transacao = new Transacao();
        transacao.setValor(Long.valueOf(dto.getValor()));
        transacao.setClienteId(cliente.getId());
        transacao.setTipo(dto.getTipo());
        transacao.setDescricao(dto.getDescricao());
        transacao.setRealizada_em(LocalDateTime.now());
        transacaoRepository.save(transacao);

        return new TransacaoResponseDTO(cliente.getLimite(), saldo);
    }

    public SaldoDTO consultarSaldo(Long id) {
        Cliente cliente = buscarCliente(id);

        SaldoDTO saldoDTO = new SaldoDTO();
        saldoDTO.setTotal(cliente.getSaldo());
        saldoDTO.setLimite(cliente.getLimite());
        saldoDTO.setData_extrato(LocalDateTime.now());

        return saldoDTO;
    }

    public ExtratoDTO consultarExtrato(Long id) {
        SaldoDTO saldoDTO = consultarSaldo(id);

        List<TransacaoDTO> transacoes = transacaoRepository.findAllByClienteId(id)
                .orElse(Collections.emptyList())
                .stream()
                .map(transacao -> {
                    TransacaoDTO transacaoDTO = new TransacaoDTO();
                    transacaoDTO.setValor(transacao.getValor());
                    transacaoDTO.setTipo(transacao.getTipo());
                    transacaoDTO.setDescricao(transacao.getDescricao());
                    transacaoDTO.setRealizada_em(transacao.getRealizada_em());

                    return transacaoDTO;
                }).toList();
        
        ExtratoDTO extratoDTO = new ExtratoDTO();
        extratoDTO.setSaldo(saldoDTO);
        extratoDTO.setUltimas_transacoes(transacoes);

        return extratoDTO;
    }

    private Long atualizarSaldo(TransacaoRequestDTO dto, Cliente cliente) {
        String tipoTransacao = dto.getTipo();
        Long valorTransacao = Long.valueOf(dto.getValor());
        Long saldo = cliente.getSaldo();
        Long limite = cliente.getLimite();

        switch (tipoTransacao) {
            case "c":
                return saldo + valorTransacao;
            case "d":
                if(saldo - valorTransacao < (limite * -1)) {
                    throw new LimiteExcedidoException("Se fudeu, não tem limite");
                } else {
                    return saldo - valorTransacao;
                }
            default:
                throw new LimiteExcedidoException("Tipo de transacao inválido");
        }
    }

    private Cliente buscarCliente(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ClienteInexistenteException("Cliente não existe"));
    }

    private void validaTranscaoRequest(TransacaoRequestDTO dto) {
        String tipo = Optional.of(dto.getTipo()).orElse("");
        if(!tipo.equalsIgnoreCase("c") && !tipo.equalsIgnoreCase("d")) {
            throw new LimiteExcedidoException("Tipo de transacao inválido");
        }

        if(Objects.isNull(dto.getDescricao()) || !dto.getDescricao().matches("^[a-zA-Z0-9]{1,10}+$")) {
            throw new LimiteExcedidoException("Descrição não pode ser vazia");
        }

        if(Objects.isNull(dto.getValor()) || !dto.getValor().matches("^[0-9]+$")) {
            throw new LimiteExcedidoException("Valor inválido");
        }

    }
}
