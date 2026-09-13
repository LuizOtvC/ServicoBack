Casa Fácil
Hub de serviços domiciliares com matchmaking inteligente

Java 21 • Spring Boot • MySQL • JWT • JPA/Hibernate

O Casa Fácil é uma plataforma que conecta contratantes a prestadores de serviços domiciliares. O sistema utiliza um algoritmo de matchmaking para calcular a compatibilidade entre cada prestador e projeto com base em habilidades, disponibilidade, orçamento, reputação e histórico.

> Front-end: [github.com/LuizOtvC/ServicoFront](https://github.com/LuizOtvC/ServicoFront)

---

## Sobre o projeto

O Casa Fácil permite que usuários publiquem projetos de serviços residenciais e recebam propostas de prestadores qualificados. O sistema calcula automaticamente um **score de compatibilidade** entre cada candidato e o projeto, considerando habilidades, disponibilidade, orçamento proposto, histórico de projetos concluídos e reputação.

---

## Funcionalidades

- Registro e autenticação de usuários com JWT
- Gerenciamento de perfil, habilidades e dias de trabalho
- Criação e gerenciamento de projetos com serviços necessários e dias disponíveis
- Envio e gerenciamento de propostas
- Motor de matchmaking com score de compatibilidade automático
- Sistema de notificações por eventos (nova proposta, aceite, recusa, conclusão)
- Avaliação mútua entre contratante e prestador ao concluir um projeto
- Atualização automática de reputação com base nas avaliações recebidas
- Controle automático de status de usuários inativos (sem login há 30 dias)

---

## Tecnologias

- Java 21
- Spring Boot 3
- Spring Data JPA + Hibernate
- MySQL 8
- JWT (autenticação stateless)
- Maven

---

## Como rodar localmente

### Pré-requisitos

- Java 21
- MySQL 8
- Maven

### Passo a passo

**1. Clone o repositório**
```bash
git clone https://github.com/LuizOtvC/ServicoBack.git
cd ServicoBack
```

**2. Crie o banco de dados no MySQL**
```sql
CREATE DATABASE casafacil;
```

**3. Configure o `application.properties`**
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/casafacil
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

**4. Rode o projeto**
```bash
mvn spring-boot:run
```

A API ficará disponível em `http://localhost:9000`.

---

## Principais endpoints

| Método | Rota | Descrição |
|---|---|---|
| POST | `/user/registrar` | Registrar novo usuário |
| POST | `/user/logar` | Login e geração de token |
| GET | `/user/perfil` | Ver perfil do usuário logado |
| PUT | `/user/atualizar` | Atualizar perfil |
| POST | `/projeto/criar` | Criar novo projeto |
| GET | `/projeto/listarFiltro` | Listar projetos disponíveis com filtros |
| POST | `/proposta/criar` | Enviar proposta para um projeto |
| GET | `/proposta/propostas/{projetoId}` | Listar propostas com score de matchmaking |
| PUT | `/proposta/aceitar/{id}` | Aceitar uma proposta |
| POST | `/avaliacao/avaliar` | Avaliar participante após conclusão |
| GET | `/mensagem/listarMensagens` | Listar notificações do usuário |

> Todas as rotas exceto `/user/registrar` e `/user/logar` exigem token JWT no header `Authorization: Bearer {token}`.

---

## Algoritmo de Matchmaking

O score de compatibilidade é calculado no momento em que uma proposta é enviada, com os seguintes pesos:

| Critério | Peso |
|---|---|
| Habilidades compatíveis | 33% |
| Aderência ao orçamento | 22% |
| Disponibilidade (dias da semana) | 28% |
| Reputação | 12% |
| Histórico de projetos concluídos | 5% |

O nível de experiência da habilidade também é considerado: Básico = 0,5 · Intermediário = 0,75 · Avançado = 1,0.
