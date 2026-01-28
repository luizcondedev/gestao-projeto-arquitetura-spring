# 🏛️ Sistema de Gestão de Projetos para Arquitetura

Este projeto é uma ferramenta de gestão voltada para arquitetos, desenvolvida para organizar projetos, prazos e recursos. Atualmente, o projeto está passando por uma **refatoração completa** da arquitetura original em Java Console para uma API REST moderna utilizando **Spring Boot**.

> ⚠️ **Status do Projeto:** Em desenvolvimento (Refatoração para Spring Web) 🚧

---

## 🚀 Objetivo da Refatoração
O objetivo principal desta etapa é evoluir a lógica de negócios anterior para um ambiente web robusto, aplicando conceitos de:
* **Inversão de Controle e Injeção de Dependência.**
* **Persistência de dados** com Spring Data JPA e PostgreSQL.
* **Arquitetura em Camadas** (Model, Repository, Service, Controller).
* **Gestão de Dependências** via Maven.

## 🛠️ Tecnologias Utilizadas (Stack)
* **Java 17** (ou a versão que você estiver usando)
* **Spring Boot 3+**
* **Spring Data JPA**
* **PostgreSQL** (Banco de dados relacional)
* **Maven** (Gerenciador de dependências)

## 📁 Estrutura de Pastas
O projeto segue o padrão Maven/Spring:
`src/main/java/com/conde/gestaoprojetosarq/`
* `model`: Entidades que representam as tabelas do banco.
* `repository`: Interfaces para comunicação com o banco de dados.
* `service`: Camada de regras de negócio.
* `controller`: Endpoints da API.

## 📝 Roadmap de Desenvolvimento
- [x] Configuração inicial do ambiente Spring com Maven.
- [x] Mapeamento das Entidades (Models) com JPA.
- [ ] Implementação dos Repositories (CRUD básico).
- [ ] Criação dos Services para regras de negócio.
- [ ] Exposição de endpoints via Controllers.
- [ ] Documentação da API com Swagger/OpenAPI.

---
Desenvolvido por Luiz Conde