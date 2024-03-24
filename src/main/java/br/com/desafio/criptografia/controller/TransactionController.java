package br.com.desafio.criptografia.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.desafio.criptografia.dto.request.TransactionRequestDTO;
import br.com.desafio.criptografia.dto.response.TransactionResponseDTO;
import br.com.desafio.criptografia.service.TransactionService;
import br.com.desafio.criptografia.service.exceptions.TransactionNotFound;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



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

    @PutMapping(value = "/{id}")
    public ResponseEntity<TransactionResponseDTO> updateTransaction(@PathVariable(name = "id") Long id, @RequestBody TransactionRequestDTO transactionRequestDTO) {
        try {
            TransactionResponseDTO updatedTransaction = transactionService.updateTransaction(id, transactionRequestDTO);
            return ResponseEntity.ok().body(updatedTransaction);

        } catch (TransactionNotFound e) {
            return ResponseEntity.notFound().build();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
        try {
            transactionService.delete(id);
            return ResponseEntity.ok().body("Transação excluída com sucesso");

        } catch (TransactionNotFound e) {
            return ResponseEntity.notFound().build();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    
    
}
