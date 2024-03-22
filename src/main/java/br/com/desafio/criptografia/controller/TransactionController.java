package br.com.desafio.criptografia.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.desafio.criptografia.dto.request.TransactionRequestDTO;
import br.com.desafio.criptografia.dto.response.TransactionResponseDTO;
import br.com.desafio.criptografia.service.TransactionService;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/transactions")
public class TransactionController {
    
    @Autowired
    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponseDTO> findById(@PathVariable Long id) {

        if(id < 0) {
            throw new IllegalArgumentException("ID inválido: " + id);
        }

        TransactionResponseDTO transaction = transactionService.findById(id);

        if (transaction == null) {
            throw new EntityNotFoundException("Transação não encontrada com o ID: " + id);
        }

        return ResponseEntity.ok(transaction);

        // return ResponseEntity.ok(transactionService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponseDTO>> findAll() {
        List<TransactionResponseDTO> transactions = transactionService.findAll();

        if(transactions.isEmpty()) {
            throw new EmptyResultDataAccessException("Nenhuma transação encontrada", 1);
        }

        return ResponseEntity.ok(transactions);
    }

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> registerNewTransaction (@RequestBody TransactionRequestDTO transactionRequestDTO, UriComponentsBuilder uriBuilder) {
        TransactionResponseDTO transResponseDTO = transactionService.registerNewTransaction(transactionRequestDTO);

        URI uri = uriBuilder.path("/transactions/{id}").buildAndExpand(transResponseDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(transResponseDTO);

    }
    
    
}
