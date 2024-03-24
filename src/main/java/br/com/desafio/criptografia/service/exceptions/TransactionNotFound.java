package br.com.desafio.criptografia.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TransactionNotFound extends RuntimeException {
    public TransactionNotFound(String msg) {
        super(msg); 
    }
}
