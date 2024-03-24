package br.com.desafio.criptografia.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "tb_transaction")
@Data
public class Transaction implements Serializable {
   // nullable = false: a coluna não pode ter valores nulos, ou seja, todos os registros devem ter um valor válido para essa coluna.
   // unique = true: Indica se os valores na coluna devem ser únicos em toda a tabela.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "userDocument", nullable = false)
    private String userDocument;

    @Column(name = "creditCardToken", nullable = false)
    private String creditCardToken;

    @Column(name = "value", nullable = false)
    private Long value;

    public Transaction() {

    }



    /*
     * O "builder pattern" é um padrão de design que é útil quando você tem muitos parâmetros opcionais em um construtor e deseja tornar a criação de objetos mais legível e fácil de usar. Ele permite que você crie objetos passo a passo, definindo apenas os valores que deseja, sem se preocupar com a ordem dos parâmetros ou com valores padrão.
    */

    @Builder
    public Transaction(Long id, String userDocument, String creditCardToken, Long value) {
        this.id = id;
        this.userDocument = userDocument;
        this.creditCardToken = creditCardToken;
        this.value = value;
    }
    
}
