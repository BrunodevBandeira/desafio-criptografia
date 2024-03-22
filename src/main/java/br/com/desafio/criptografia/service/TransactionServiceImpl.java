package br.com.desafio.criptografia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import br.com.desafio.criptografia.dto.request.TransactionRequestDTO;
import br.com.desafio.criptografia.dto.response.TransactionResponseDTO;
import br.com.desafio.criptografia.entity.Transaction;
import br.com.desafio.criptografia.repository.TransactionRepository;
import br.com.desafio.criptografia.util.TransactionMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
 
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private final TransactionRepository transactionRepository;

    @Autowired
    private final TransactionMapper transactionMapper;
 
     
    @Override
    public TransactionResponseDTO findById(Long id) {
        return transactionMapper.toTransactionDTO(returnTransaction(id));
    }

    @Override
    public List<TransactionResponseDTO> findAll() {
        return transactionMapper.toDTO(transactionRepository.findAll());
    }

    @Override
    public TransactionResponseDTO registerNewTransaction(TransactionRequestDTO transactionRequestDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'registerNewTransaction'");
    }

    @Override
    public TransactionResponseDTO updateTransaction(Long id, TransactionRequestDTO transactionRequestDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateTransaction'");
    }

    @Override
    public String delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    private Transaction returnTransaction(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("==> ID não encontrado <=="));

    }
    
}
