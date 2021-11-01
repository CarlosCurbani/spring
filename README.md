# Spring API RESTful
Este projeto tem como objetivo ler um arquivo CSV, gravar no banco de dados e disponibilizar através de um serviço Rest os piores filmes ganhadores de um mesmo produtor que obteve o Menor intevalo entre duas premiações e o Maior intervalo

## Tecnologias
Este projeto foi criado com:
* Spring Boot: 2.5.6
* Java: 8
* opencsv: 5.5.2
* H2 database

## Configuração 
Para rodar esse projeto será necessário possuir o maven configurado, executar o comando para start da aplicação:

```
// iniciar a aplicação 
$ mvn spring-boot:run

// exeuctar os testes
$ mvn test

```
### arquivo CVS
O arquivo CSV fica dentro da pasta Resources. O nome do CSV usado pela aplicação pode ser alterado no arquivo application.properties pela propriedade "csv.name.file".

#### Padrão CSV
É necessário que o arquivo CSV tenha o header do seguinte padrão:
year;title;studios;producers;winner

### endPoint
GET: http://localhost:8080/movies


