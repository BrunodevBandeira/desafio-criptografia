package br.com.desafio.criptografia.util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.desafio.criptografia.dto.request.TransactionRequestDTO;
import br.com.desafio.criptografia.dto.response.TransactionResponseDTO;
import br.com.desafio.criptografia.entity.Transaction;
import br.com.desafio.criptografia.service.SecurityComponent.SecurityComponent;

@Component
public class TransactionMapper {

    @Autowired
    private SecurityComponent securityComponent;

    
    public Transaction toTransaction(TransactionRequestDTO  transactionRequestDTO) {
        // toTransaction => converte um obj TransactionRequestDTO para um obj Transaction

        String encryptedUserDocument = securityComponent.encryptFields(transactionRequestDTO.getUserDocument());
        String encryptedCreditCardToken = securityComponent.encryptFields(transactionRequestDTO.getCreditCardToken());
        System.out.println("=> T1 " + encryptedUserDocument);
        System.out.println("=> T2 " + encryptedCreditCardToken);

        return Transaction.builder()
                .userDocument(encryptedUserDocument)
                .creditCardToken(encryptedCreditCardToken)
                .valor(transactionRequestDTO.getValor())
                .build();
    }

    public TransactionResponseDTO toTransactionDTO(Transaction transaction) {
        // toTransactionDTO: Converte um objeto Transaction em um objeto TransactionResponseDTO.
        
        String decryptedUserDocument = securityComponent.decryptFields(transaction.getUserDocument());
        String decryptedCreditCardToken = securityComponent.decryptFields(transaction.getCreditCardToken());

        return new TransactionResponseDTO(transaction.getId(), decryptedUserDocument, 
                                    decryptedCreditCardToken, transaction.getValor());

    }

        public List<TransactionResponseDTO> toDTO(List<Transaction> transactionList) {
        // toPeopleDTO: Converte uma lista de objetos transactionList em uma lista de TransactionResponseDTO.
        return transactionList.stream().map(TransactionResponseDTO::new).collect(Collectors.toList());
        
    }

    public void updateTransactionData(Transaction transaction, TransactionRequestDTO transactionRequestDTO) {
            // updateTransactionData: Atualiza os dados de um objeto Transaction com base nos dados fornecidos em um objeto TransactionRequestDTO.

            transaction.setUserDocument(transactionRequestDTO.getUserDocument());
            transaction.setCreditCardToken(transactionRequestDTO.getCreditCardToken());
            transaction.setValor(transactionRequestDTO.getValor());
    }

    /*
     * Os dados já estão sendo criptografados 
     */

}
