package com.clemer.rinha.repositories;

import com.clemer.rinha.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Cliente, Long> {
}
