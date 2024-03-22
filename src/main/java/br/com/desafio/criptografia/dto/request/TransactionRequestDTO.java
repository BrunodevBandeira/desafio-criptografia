package br.com.desafio.criptografia.dto.request;

import lombok.Data;

@Data
public class TransactionRequestDTO {
   //REQUEST => Dados que estão sendo enviados 

    private String userDocument;
    private String creditCardToken;
    private Long value;
    
}
