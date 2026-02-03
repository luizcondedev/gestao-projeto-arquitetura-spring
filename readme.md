# 🏛️ Sistema de Gestão de Projetos para Arquitetura

API REST completa para gerenciamento de projetos arquitetônicos, desenvolvida com Spring Boot. O sistema permite cadastro e gestão de arquitetos, clientes e projetos com regras de negócio robustas e validações de integridade.

> 📌 **Status do Projeto:** API REST funcional com CRUD completo | Documentação Swagger implementada

---

## 📋 Índice

- [Sobre o Projeto](#sobre-o-projeto)
- [Funcionalidades](#funcionalidades)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Arquitetura](#arquitetura)
- [Pré-requisitos](#pré-requisitos)
- [Instalação e Configuração](#instalação-e-configuração)
- [Como Executar](#como-executar)
- [Como Testar](#como-testar)
- [Documentação da API](#documentação-da-api)
- [Regras de Negócio](#regras-de-negócio)
- [Roadmap](#roadmap)
- [Autor](#autor)

---

## 🎯 Sobre o Projeto

Este projeto nasceu como uma ferramenta de gestão em Java Console e passou por uma **refatoração completa** para se tornar uma API REST moderna. O objetivo é fornecer uma solução robusta para arquitetos organizarem seus projetos, clientes e recursos de forma eficiente.

A aplicação implementa conceitos modernos de desenvolvimento como:
- ✅ **Inversão de Controle (IoC)** e **Injeção de Dependência (DI)**
- ✅ **Persistência de Dados** com JPA/Hibernate
- ✅ **Arquitetura em Camadas** (MVC)
- ✅ **RESTful API** com boas práticas HTTP
- ✅ **Validações de Integridade** e regras de negócio complexas

---

## ⚙️ Funcionalidades

### 👷 Gestão de Arquitetos
- ✅ Cadastro de arquitetos com validação de CPF, Email e CAU únicos
- ✅ Listagem de todos os arquitetos
- ✅ Busca de arquiteto por CPF
- ✅ Atualização de dados do arquiteto
- ✅ Exclusão de arquiteto (bloqueada se houver projetos associados)
- ✅ Visualização de projetos por arquiteto

### 👥 Gestão de Clientes
- ✅ Cadastro de clientes com validação de CPF e Email únicos
- ✅ Listagem de todos os clientes
- ✅ Busca de cliente por CPF
- ✅ Atualização de dados do cliente
- ✅ Exclusão de cliente (remove projetos associados em cascata)
- ✅ Visualização de projetos por cliente

### 🏗️ Gestão de Projetos
- ✅ Criação de projetos vinculados a arquiteto e cliente
- ✅ Listagem de todos os projetos
- ✅ Atualização de dados do projeto
- ✅ Transferência de projeto entre arquitetos
- ✅ Exclusão de projeto
- ✅ Limite de 2 projetos por arquiteto
- ✅ Limite de 1 projeto por cliente

---

## 🛠️ Tecnologias Utilizadas

### Backend
- **Java 17** - Linguagem principal
- **Spring Boot 3.5.10** - Framework principal
- **Spring Data JPA** - Persistência de dados
- **Spring Web** - Criação de APIs REST
- **Hibernate** - ORM (Object-Relational Mapping)
- **Lombok** - Redução de boilerplate
- **Swagger/OpenAPI** - Documentação automática da API

### Banco de Dados
- **PostgreSQL** - Banco de dados relacional

### Ferramentas
- **Maven** - Gerenciamento de dependências
- **Postman** - Testes de API (opcional)
- **Git** - Controle de versão

---

## 🏗️ Arquitetura

O projeto segue o padrão de **arquitetura em camadas**:
```
src/main/java/com/conde/gestaoprojetosarq/
│
├── 📁 model/                    # Entidades JPA
│   ├── Arquiteto.java
│   ├── Cliente.java
│   ├── Projeto.java
│   └── dto/                     # Data Transfer Objects
│       ├── ArquitetoDTO.java
│       ├── ClienteDTO.java
│       ├── ProjetoDTO.java
│       └── ProjetoUpdateDTO.java
│
├── 📁 repository/               # Camada de acesso a dados
│   ├── ArquitetoRepository.java
│   ├── ClienteRepository.java
│   └── ProjetoRepository.java
│
├── 📁 service/                  # Lógica de negócio
│   ├── ArquitetoService.java
│   ├── ClienteService.java
│   └── ProjetoService.java
│
├── 📁 controller/               # Endpoints REST
│   ├── ArquitetoController.java
│   ├── ClienteController.java
│   └── ProjetoController.java
│
├── 📁 infrastructure/
│   └── exceptions/              # Exceções customizadas
│       └── ConflictException.java
    └── config/                   # Configurações
        └── SwaggerConfig.java       # Configuração do Swagger
```

---

## 📋 Pré-requisitos

Antes de começar, certifique-se de ter instalado:

- ☕ **Java 17** ou superior ([Download JDK](https://www.oracle.com/java/technologies/downloads/))
- 🐘 **PostgreSQL 12** ou superior ([Download PostgreSQL](https://www.postgresql.org/download/))
- 📦 **Maven 3.8+** (geralmente já vem com IDEs modernas)
- 💻 **IDE** (IntelliJ IDEA, Eclipse, VS Code com extensões Java)
- 🔧 **Git** (opcional, para clonar o repositório)

---

## 🚀 Instalação e Configuração

### 1️⃣ Clone o repositório
```bash
git clone https://github.com/luizcondedev/gestao-projeto-arquitetura-spring
cd gestao-projeto-arquitetura-spring
```

### 2️⃣ Configure o banco de dados PostgreSQL

Crie um banco de dados no PostgreSQL:
```sql
CREATE DATABASE db_projetos_arq;
```

### 3️⃣ Configure as credenciais do banco

Edite o arquivo `src/main/resources/application.properties`:
```properties
# Configuração do Banco de Dados
spring.datasource.url=jdbc:postgresql://localhost:5432/db_projetos_arq
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

# Configuração do JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Configuração da aplicação
server.port=8080
```

> ⚠️ **Importante:** Substitua `seu_usuario` e `sua_senha` pelas suas credenciais do PostgreSQL!

### 4️⃣ Instale as dependências
```bash
mvn clean install
```

---

## ▶️ Como Executar

### Via IDE (IntelliJ IDEA / Eclipse)
1. Abra o projeto na IDE
2. Localize a classe `GestaoprojetosarqApplication.java`
3. Clique com botão direito → **Run**

### Via Terminal (Maven)
```bash
mvn spring-boot:run
```

### Via JAR (após build)
```bash
# Gerar o JAR
mvn clean package

# Executar o JAR
java -jar target/gestaoprojetosarq-0.0.1-SNAPSHOT.jar
```

A aplicação estará disponível em: **`http://localhost:8080`**

---

## 🧪 Como Testar

### 1️⃣ Via Swagger UI (Recomendado) 📚

Acesse a documentação interativa:
```
http://localhost:8080/swagger-ui.html
```

Na interface do Swagger você pode:
- ✅ Visualizar todos os endpoints disponíveis
- ✅ Ver exemplos de request/response
- ✅ **Testar diretamente** clicando em "Try it out"
- ✅ Ver schemas dos DTOs e Models

### 2️⃣ Via Postman / Insomnia

Importe a collection ou teste manualmente os endpoints:

#### Exemplo: Criar um Arquiteto
```http
POST http://localhost:8080/arquiteto
Content-Type: application/json

{
  "nome": "João Silva",
  "email": "joao@email.com",
  "cpf": "123.456.789-00",
  "cau": "A12345"
}
```

#### Exemplo: Listar todos os Arquitetos
```http
GET http://localhost:8080/arquiteto/buscar
```

#### Exemplo: Atualizar Arquiteto
```http
PUT http://localhost:8080/arquiteto/1
Content-Type: application/json

{
  "nome": "João Silva Atualizado",
  "email": "joao.novo@email.com"
}
```

#### Exemplo: Deletar Arquiteto
```http
DELETE http://localhost:8080/arquiteto/1
```

### 3️⃣ Via cURL
```bash
# Criar arquiteto
curl -X POST http://localhost:8080/arquiteto \
  -H "Content-Type: application/json" \
  -d '{"nome":"João Silva","email":"joao@email.com","cpf":"123.456.789-00","cau":"A12345"}'

# Listar arquitetos
curl http://localhost:8080/arquiteto/buscar

# Buscar por CPF
curl http://localhost:8080/arquiteto/buscar/123.456.789-00
```

---

## 📖 Documentação da API

### Endpoints Principais

#### 👷 Arquitetos

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/arquiteto` | Criar novo arquiteto |
| `GET` | `/arquiteto/buscar` | Listar todos os arquitetos |
| `GET` | `/arquiteto/buscar/{cpf}` | Buscar arquiteto por CPF |
| `GET` | `/arquiteto/buscar/projetos/{cpf}` | Listar projetos do arquiteto |
| `PUT` | `/arquiteto/{id}` | Atualizar dados do arquiteto |
| `DELETE` | `/arquiteto/{id}` | Deletar arquiteto |

#### 👥 Clientes

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/cliente` | Criar novo cliente |
| `GET` | `/cliente/buscar` | Listar todos os clientes |
| `GET` | `/cliente/buscar/{cpf}` | Buscar cliente por CPF |
| `GET` | `/cliente/buscar/projetos/{cpf}` | Listar projetos do cliente |
| `PUT` | `/cliente/{id}` | Atualizar dados do cliente |
| `DELETE` | `/cliente/{id}` | Deletar cliente (e seus projetos) |

#### 🏗️ Projetos

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/projeto` | Criar novo projeto |
| `GET` | `/projeto/buscar` | Listar todos os projetos |
| `PUT` | `/projeto/{id}` | Atualizar projeto |
| `DELETE` | `/projeto/{id}` | Deletar projeto |

---

## 📜 Regras de Negócio

### Validações de Criação

#### Arquiteto
- ❌ CPF deve ser único no sistema
- ❌ Email deve ser único no sistema
- ❌ CAU (Conselho de Arquitetura e Urbanismo) deve ser único
- ✅ Todos os campos são obrigatórios

#### Cliente
- ❌ CPF deve ser único no sistema
- ❌ Email deve ser único no sistema
- ✅ Todos os campos são obrigatórios

#### Projeto
- ✅ Deve estar vinculado a um Arquiteto existente
- ✅ Deve estar vinculado a um Cliente existente
- ❌ Arquiteto não pode ter mais de **2 projetos** simultaneamente
- ❌ Cliente não pode ter mais de **1 projeto** simultaneamente

### Regras de Atualização

#### Arquiteto / Cliente
- ✅ Campos não enviados são mantidos
- ❌ Não pode alterar para CPF/Email/CAU já existente em outro registro

#### Projeto
- ✅ Pode trocar de arquiteto (validação de limite aplicada)
- ❌ **NÃO** pode trocar de cliente (campo fixo após criação)

### Regras de Exclusão

#### Arquiteto
- ❌ **Bloqueado** se tiver projetos associados
- 📝 Mensagem: "O arquiteto tem X projeto(s) associado(s)!"

#### Cliente
- ✅ Deleta o cliente **E** todos os projetos dele (cascade delete)
- 📝 Mensagem: "Cliente deletado com sucesso. X projeto(s) também foi(foram) deletado(s)"

#### Projeto
- ✅ Pode ser deletado livremente (não tem dependentes)

---

## 🗺️ Roadmap

### ✅ Concluído
- [x] Configuração inicial do ambiente Spring Boot
- [x] Mapeamento de Entidades (JPA/Hibernate)
- [x] Implementação de Repositories
- [x] Criação de Services com regras de negócio
- [x] Exposição de Controllers REST
- [x] CRUD completo (Create, Read, Update, Delete)
- [x] Validações de integridade referencial
- [x] DTOs para separação de responsabilidades
- [x] Documentação com Swagger/OpenAPI

### 🚧 Em Desenvolvimento
- [ ] Tratamento de erros global com `@ControllerAdvice`
- [ ] Validações automáticas com Bean Validation
- [ ] Testes unitários com JUnit 5 e Mockito
- [ ] Testes de integração

### 🔮 Futuro
- [ ] **Autenticação e Autorização** com Spring Security
    - JWT (JSON Web Tokens)
    - Roles e permissões (ADMIN, ARQUITETO, CLIENTE)
    - Login e registro de usuários
- [ ] **Frontend** com Angular
    - Interface responsiva
    - Dashboard com métricas
    - Formulários de cadastro e edição
- [ ] Paginação e ordenação de listagens
- [ ] Filtros avançados de busca
- [ ] Upload de imagens/arquivos de projetos
- [ ] Geração de relatórios em PDF
- [ ] Notificações por email
- [ ] Deploy em nuvem (AWS/Heroku)

---

## 🐛 Problemas Conhecidos

Nenhum problema crítico conhecido no momento. Para reportar bugs ou sugerir melhorias, abra uma [issue](https://github.com/seu-usuario/gestaoprojetosarq/issues).

---

## 🤝 Contribuindo

Contribuições são bem-vindas! Para contribuir:

1. Faça um Fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/MinhaFeature`)
3. Commit suas mudanças (`git commit -m 'Adiciona MinhaFeature'`)
4. Push para a branch (`git push origin feature/MinhaFeature`)
5. Abra um Pull Request

---

## 👨‍💻 Autor

**Luiz Conde**

- 📧 Email: luizconde.deev@gmail.com
- 💼 LinkedIn: [linkedin](https://www.linkedin.com/in/luiz-conde-dev/)
- 🐙 GitHub: [@luizcondedev](https://github.com/luizcondedev)





---

<div align="center">

**⭐ Se este projeto foi útil para você, considere dar uma estrela!**

Desenvolvido com ☕ e 💙 por Luiz Conde

</div>