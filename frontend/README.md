# Frontend – pt2025-dev-mm

Formulário frontend desenvolvido em React para coleta e validação de dados pessoais e dados de cartão de crédito, com envio para backend via API.

---

## 🛠️ Tecnologias Utilizadas

- ⚛️ React 18+
- 📝 TypeScript
- 🧩 react-hook-form (formulário e validação)
- ✅ yup (validação de esquema)
- 🎨 styled-components (estilização)
- 📡 Axios (requisições HTTP)
- 🚀 Vite (ferramenta de build e dev server)

---

## 🎯 Funcionalidades

- ✔️ Validação completa dos campos usando schema `yup`
- 🎭 Máscaras para CPF, número do cartão, CVV e validade do cartão
- 💳 Exibição da bandeira do cartão de crédito com base no número informado
- 🔞 Validação da idade mínima (18 anos)
- ⏳ Validação se o cartão está vencido
- 📤 Envio dos dados para backend via API REST

---

## 📂 Estrutura de arquivos principal

- `src/App.tsx` - componente principal e formulário
- `src/components/Input.tsx` - componente de input customizado com validação visual
- `src/components/Button.tsx` - botão estilizado
- `src/components/Card.tsx` - container estilizado do formulário
- `src/utils/masks.ts` - funções para máscaras de entrada (CPF, cartão, CVV, etc)
- `src/utils/cardBrand.ts` - lógica para detectar bandeira do cartão
- `src/services/api.ts` - instância Axios para comunicação com backend

---

## ⚙️ Instalação

1. **Clone o repositório:**

```bash
git clone https://github.com/seu-usuario/pt2025-dev-mm.git
```

2. **Acesse a pasta do projeto:**

```bash
cd pt2025-dev-mm/frontend
```

3. **Instale as dependências:**

```bash
npm install
```

ou

```bash
yarn
```

## 🔧 Configuração

Se necessário, crie um arquivo `.env` na raiz do projeto e adicione as variáveis de ambiente conforme o exemplo abaixo:

```
VITE_API_URL=http://localhost:8080
```

## ▶️ Como executar

Para iniciar o projeto em modo de desenvolvimento:

```bash
npm run dev
```

ou

```bash
yarn dev
```

Abra seu navegador e acesse http://localhost:5173 (ou a porta informada no terminal).

## 📜 Scripts Disponíveis

- `dev` – Inicia o servidor de desenvolvimento com Vite
- `build` – Compila o projeto para produção
- `preview` – Visualiza o build de produção localmente
- `lint` – Executa o ESLint no projeto
