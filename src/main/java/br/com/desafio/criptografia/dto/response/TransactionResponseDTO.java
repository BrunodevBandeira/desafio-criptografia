package br.com.desafio.criptografia.dto.response;

import br.com.desafio.criptografia.entity.Transaction;
import lombok.Data;

@Data
public class TransactionResponseDTO {
    // RESPONSE => respostas do banco

    private Long id;
    private String userDocument;
    private String creditCardToken;
    private Long valor;

    // Construtor para aceitar os campos criptografados
    public TransactionResponseDTO(Long id, String userDocument, String creditCardToken, Long valor) {
        this.id = id;
        this.userDocument = userDocument;
        this.creditCardToken = creditCardToken;
        this.valor = valor;
    }


    // Construtor para converter de Transaction para TransactionResponseDTO
    public TransactionResponseDTO(Transaction transaction) {
        // facilitar a conversão de um objeto Transaction em um objeto TransactionResponseDTO
        this.id = transaction.getId();
        this.userDocument = transaction.getUserDocument();
        this.creditCardToken = transaction.getCreditCardToken();
        this.valor = transaction.getValor();
    }
}
