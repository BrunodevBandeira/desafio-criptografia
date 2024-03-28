<h1 align="center">
   Desafio Backend-br    <br />
   Criptografia   <br />   <img align="center" alt="html" height="50px" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-plain.svg" />
</h1>


Este projeto foi feito como desafio do repositorio  [backend-br](https://github.com/backend-br/desafios)


## Tecnologias 

- Spring Boot
- Spring MVC
- Spring JPA
- Spring security
- H2

 ## Sobre o desafio <img align="center" alt="html" height="50px" src="https://cultofthepartyparrot.com/parrots/hd/hackerparrot.gif" />

 Implementação de criptografia transparente para campos sensíveis em entidades, onde os campos são entregues descriptografados nas requisições das APIs e persistidos criptografados no banco de dados.


## Sobre o projeto. <img align="center" alt="html" height="50px" src="https://cultofthepartyparrot.com/parrots/hd/spyparrot.gif" />

Neste desafio foi criado uma CRUD(Create, Read, Update e Delete) simples no Spring-boot seguindo o Padrão MVC (Model View Controller), foi utilizado o banco de dados H2 e para criptografia foi utilizado na aplicação o algoritmo AES existente na biblioteca do spring-security.
 
## Teste da Api no Postman <img align="center" alt="html" height="50px" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/postman/postman-plain.svg" />
Para o post utilize esse JSON
```
 {
    "userDocument": "3214587",
    "creditCardToken": "965478",
    "valor": 202
}
```
Enviando dados para criar a entidade. 

![image](https://github.com/BrunodevBandeira/desafio-criptografia/assets/68456392/1287a5c1-0a07-4922-a702-0b6caa8b195d)

Dados persistidos já criptografados no banco de dados H2

![image](https://github.com/BrunodevBandeira/desafio-criptografia/assets/68456392/2a3befdd-d789-4d23-b74a-14b242bfcc8a)


Pegando dados descriptografados pelo id

![image](https://github.com/BrunodevBandeira/desafio-criptografia/assets/68456392/c1f6f2cb-ed09-4b64-9969-06d850625463)




 

  <h1 align="center">
                                                                        <img align="center" alt="css" height="80px" src="https://cultofthepartyparrot.com/parrots/hd/dealwithitparrot.gif" />
                                                                          </h1>
