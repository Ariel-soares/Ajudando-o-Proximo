# Ajudando o próximo
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/Ariel-soares/Desafio2/blob/main/LICENSE) 

# Sobre o projeto

Ajudando o próximo é uma aplicação backend criada como resposta ao desafio 2 no estágio de Backend na UOL Compass.

O projeto consiste em um sistema de manejamento de itens advindos de doeações, que serão direcionadas a centros de distribuição e então para abrigos, administrando assim os estoques.

# Tecnologias utilizadas
- Java
- JPA / Hibernate
- Maven

## Modelo de domínio
![Modelo-De-Cominio](https://github.com/Ariel-soares/Ajudando-o-Proximo/blob/main/assets/domain-model.jpg)

## Implantação em produção
- Banco de dados: Postgresql

# Como executar o projeto
Pré-requisitos: Java 17,
Maven

1º Passo: Clonar o projeto

```bash
# clonar repositório
git clone https://github.com/Ariel-soares/Ajudando-o-Proximo
```

2ºPasso: Entrar no arquivo persistence.xml e trocar o valor das propriedades "user" e "password" pelas credenciais de usuário do seu banco de dados

![Persistence directory](https://github.com/Ariel-soares/Ajudando-o-Proximo/blob/main/assets/persistence-files.jpg)

![DB-credentials](https://github.com/Ariel-soares/Ajudando-o-Proximo/blob/main/assets/DB-credentials.jpg)

(Utilize PostgreSQL para evitar problemas de compatibilidade).
Mas caso queira trocar a base de dados para uma de sua escolha, basta retirar a dependência do PostgreSQL no arquivo POM.xml e adicionar a de seu gosto.

3º Passo: Executar o projeto via linha de comando

```bash
# entrar na pasta do projeto
# executar comandos maven para build do projeto
mvn clean install
mvn clean compile
mvn package
# entrar na pasta target
cd target
# executar o projeto
java -jar arquivo.jar que está na pasta
```
# Autor

Ariel Soares Franco

https://www.linkedin.com/in/ariel-soares/

# Agradecimentos

Deixo aqui meu mais profundo obrigado aos orientadores:

- Franciele Ciostek
- Diego Bonetti
- Gabryel Airez de Melo

Que apesar de terem suas próprias demandas internas na empresa, estiveram a disposição para me ajudar em toda e qualquer situação independente de horário.

Também deixo aqui meus agradecimentos aos mebros do meu squad:

- Diego Pimenta
- Gerson Luís Soares
- Luís Otavio de Siqueira
- Pedro Roberto Chicuta

Que apesar de não compartilharmos código ou lógica de programação mantiveram a boa comunicação e o incentivo mesmo o projeto sendo de avaliação individual.)
