📦 Prova Técnica – pt2025-dev-mm

Este repositório contém a solução para a prova técnica composta por:

🖥️ /frontend: Aplicação React com TypeScript para coleta e validação de dados pessoais e de cartão de crédito.

⚙️ /backend: API REST em Spring Boot responsável por receber e processar os dados enviados pelo frontend.

📁 Estrutura do Repositório

```bash
├── frontend/ # Aplicação React
├── backend/ # API Spring Boot
└── README.md # Este arquivo
```

🚀 Como Rodar o Projeto

**Pré-requisitos:**

- Node.js (para o frontend)
- React (com Vite)
- Java 17+
- Spring Boot
- Maven

---

🔧 **Backend (Spring Boot)**

1. Acesse a pasta do backend:

```bash
cd backend
```

2. Verifique o arquivo `src/main/resources/application.properties`.  
   Ele já está configurado para ambiente de desenvolvimento e não requer ajustes adicionais para rodar o backend.

3. Rode a aplicação:

```bash
./mvnw spring-boot:run
```

ou

```bash
mvn spring-boot:run
```

A API estará disponível em: http://localhost:8080

---

💻 **Frontend (React)**

1. Acesse a pasta do frontend:

```bash
cd frontend
```

2. Instale as dependências:

```bash
npm install
```

ou

```bash
yarn
```

3. Configure a URL da API no arquivo `.env` do frontend:

```ini
VITE_API_URL=http://localhost:8080
```

4. Rode o app:

```bash
npm run dev
```

ou

```bash
yarn dev
```

Abra http://localhost:5173 no navegador.

---

✅ **Comandos Rápidos**

- **Frontend:**
  | Comando | Ação |
  | --------------- | --------------------------------- |
  | `npm run dev` | Inicia o frontend em modo dev |
  | `npm run build` | Gera build de produção |
  | `npm test` | Executa os testes (se existirem) |

- **Backend:**
  | Comando | Ação |
  | ---------------------- | -------------------------------- |
  | `mvn spring-boot:run` | Inicia o backend em modo dev |
  | `mvn clean package` | Compila e empacota o projeto |

---

📝 **Observações**

- Certifique-se de que o backend esteja rodando antes de enviar dados pelo frontend.
- Para facilitar testes locais, você pode usar ferramentas como Postman ou Insomnia para testar os endpoints da API.
- Máscaras, validações de idade e validade do cartão são feitas no frontend com yup e react-hook-form.
