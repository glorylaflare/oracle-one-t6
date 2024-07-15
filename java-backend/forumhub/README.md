# 🌐 Projeto ForumHub da Alura
Projeto desenvolvido para o programa ONE, com o intuito de mostrar meus conhecimentos em Java com Spring Boot.

## Sobre o projeto

No projeto foi desenvolvida uma API para a manipulação de tópicos de um forum estudantil da Alura. No projeto você pode criar um tópico, atualizar, deletar e listar todos os tópicos ou apenas um tópico específico utilizando o seu id. Na classe tópico. Todos os procedimentos de manipulação do banco de dados requerem um token de autenticação, sem o token o acesso é negado ao usuário, portanto apenas usuários cadastrados no banco de dados podem manipular os tópicos.

Foi um projeto simples apenas com os requisitos obrigatórios e alguns adicionais, o projeto tem bastante margem para crescimento e melhorias, além da construção de um sistema mais robusto com outras tabelas de usuários e cursos se conectando a tabela tópicos. **Porém todos os critérios propostos no desafio foram cumpridos.** 

Foi utilizado no projeto o Spring Boot, com as dependências do PostgreSQL, Lombok, Spring JPA, Spring Boot DevTools, Flyway, Validation, Spring Security e Spring Web. O java utilizado foi o Java 17 e o banco de dados foi utilizado o PostgreSQL, optei usar o PostgreSQL por motivos de praticidade e por eu já ter mais domínio na ferramenta, o software de consultas dos endpoints foi o Insomnia.

## Tecnologias
![Skills](https://skillicons.dev/icons?i=java,spring,postgres)