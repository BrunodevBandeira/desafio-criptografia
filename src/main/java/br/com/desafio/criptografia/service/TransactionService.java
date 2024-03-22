package br.com.desafio.criptografia.service;

import java.util.List;

import br.com.desafio.criptografia.dto.request.TransactionRequestDTO;
import br.com.desafio.criptografia.dto.response.TransactionResponseDTO;

public interface TransactionService {

    TransactionResponseDTO findById(Long id); 
    List<TransactionResponseDTO> findAll();
    TransactionResponseDTO registerNewTransaction(TransactionRequestDTO transactionRequestDTO);
    TransactionResponseDTO updateTransaction(Long id, TransactionRequestDTO transactionRequestDTO);
    String delete(Long id);
    
}
