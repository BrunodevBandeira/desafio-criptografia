package br.com.desafio.criptografia.dto.response;

import br.com.desafio.criptografia.entity.Transaction;
import lombok.Data;

@Data
public class TransactionResponseDTO {
    // RESPONSE => respostas do banco

    private Long id;
    private String userDocument;
    private String creditCardToken;
    private Long value;

    public TransactionResponseDTO(Transaction transaction) {
        // facilitar a conversão de um objeto Transaction em um objeto TransactionResponseDTO
        this.id = transaction.getId();
        this.userDocument = transaction.getUserDocument();
        this.creditCardToken = transaction.getCreditCardToken();
        this.value = transaction.getValue();
    }
    
}
