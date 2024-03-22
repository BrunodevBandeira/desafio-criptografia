package br.com.desafio.criptografia.util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.desafio.criptografia.dto.request.TransactionRequestDTO;
import br.com.desafio.criptografia.dto.response.TransactionResponseDTO;
import br.com.desafio.criptografia.entity.Transaction;

@Component
public class TransactionMapper {
    
    public Transaction toTransaction(TransactionRequestDTO  transactionRequestDTO) {
        // toTransaction => converte um obj TransactionRequestDTO para um obj Transaction

        return Transaction.builder()
                .userDocument(transactionRequestDTO.getUserDocument())
                .creditCardToken(transactionRequestDTO.getCreditCardToken())
                .value(transactionRequestDTO.getValue())
                .build();
    }

    public TransactionResponseDTO toTransactionDTO(Transaction transaction) {
        // toTransactionDTO: Converte um objeto Transaction em um objeto TransactionRequestDTO.
        return new TransactionResponseDTO(transaction);
    }

        public List<TransactionResponseDTO> toDTO(List<Transaction> transactionList) {
        // toPeopleDTO: Converte uma lista de objetos Person em uma lista de PersonResponseDTO.
        return transactionList.stream().map(TransactionResponseDTO::new).collect(Collectors.toList());
    }

    public void updateTransactionData(Transaction transaction, TransactionRequestDTO transactionRequestDTO) {
            // updateTransactionData: Atualiza os dados de um objeto Transaction com base nos dados fornecidos em um objeto TransactionRequestDTO.

            transaction.setUserDocument(transactionRequestDTO.getUserDocument());
            transaction.setCreditCardToken(transactionRequestDTO.getCreditCardToken());
            transaction.setValue(transactionRequestDTO.getValue());
    }

}
