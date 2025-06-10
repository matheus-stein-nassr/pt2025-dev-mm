# ⚙️ Backend – pt2025-dev-mm

Este é o backend da aplicação de checkout de pagamento, desenvolvido com **Spring Boot 3.5.0** e seguindo boas práticas de arquitetura em camadas.

---

## 🛠️ Tecnologias Utilizadas

- ☕ Java 17
- 🌱 Spring Boot 3.5.0
- 🐘 Maven
- ✅ Jakarta Bean Validation

---

## 📋 Pré-requisitos

- [Java 17+](https://adoptium.net/pt/temurin/releases/?version=17)
- [Maven 3.9+](https://maven.apache.org/download.cgi) (opcional, caso não use o wrapper)

---

## 📂 Estrutura de Pastas

```bash
src/main/java/com/pt2025/backend/
├── controller/
├── dto/
├── exception/
├── model/
├── service/
├── utils/
├── validator/
```

---

## 🚀 Como Executar

1. **Clone o repositório:**

   ```bash
   git clone <URL_DO_REPOSITORIO>
   cd backend
   ```

2. **Execute o projeto usando o Maven Wrapper:**
   ```bash
   ./mvnw spring-boot:run
   ```
   ou, se você usa Maven global:
   ```bash
   mvn spring-boot:run
   ```

O servidor estará rodando em: [http://localhost:8080](http://localhost:8080)

---

## 📫 Exemplo de Requisição ao Endpoint de Pagamento

```bash
curl -X POST http://localhost:8080/api/payments \
  -H "Content-Type: application/json" \
  -d '{
        "nomeCompleto": "Ana Souza",
        "cpf": "987.654.321-00",
        "dataNascimento": "1990-04-15",
        "numeroCartao": "5500 0000 0000 0004",
        "nomeImpresso": "ANA SOUZA",
        "validade": "08/30",
        "cvv": "456",
        "email": "ana.souza@email.com"
      }'
```

---

## 🧰 Comandos Maven Úteis

| Comando               | Ação                             |
| --------------------- | -------------------------------- |
| `mvn clean install`   | Instala dependências e compila   |
| `mvn spring-boot:run` | Inicia o backend em modo dev     |
| `mvn test`            | Executa os testes (se existirem) |

---

## 📝 Observações

- O arquivo `src/main/resources/application.properties` já está configurado para ambiente de desenvolvimento.
- Não é necessário configurar banco de dados.
- Para testar os endpoints, utilize ferramentas como Postman ou Insomnia, ou o exemplo de `curl` acima.
