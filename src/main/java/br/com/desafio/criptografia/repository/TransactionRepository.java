package br.com.desafio.criptografia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafio.criptografia.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
}
