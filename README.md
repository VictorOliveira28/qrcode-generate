# 🧾 QR Code Generator

Um gerador de QR Code simples e eficiente, desenvolvido com **Java 17**, **Spring Boot** e **Docker**. Permite gerar códigos QR a partir de um texto, salvar no banco de dados e fazer download da imagem gerada.

---

## 🚀 Tecnologias Utilizadas

- [Java 17](https://openjdk.org/projects/jdk/17/)
- [Spring Boot 3](https://spring.io/projects/spring-boot)
- [Docker](https://www.docker.com/)
- [PostgreSQL](https://www.postgresql.org/)
- [ZXing](https://github.com/zxing/zxing) (para gerar o QRCode)

---

## 📦 Como rodar o projeto com Docker

1. **Clone o repositório:**

```bash
git clone https://github.com/VictorOliveira28/qrcode-generate.git
```

# Crie um arquivo .env com as variáveis de ambiente:
DB_USERNAME=seu_usuario
DB_PASSWORD=sua_senha
SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/postgres

## Mude o server.port no application.properties para 8081

<img width="945" height="459" alt="image" src="https://github.com/user-attachments/assets/d48bfe20-79b6-4d13-94ea-abe9d952aff0" />



## Execute o Docker Compose:
```
docker-compose up --build
```

## 🛠 Testando a API com Postman

Para facilitar os testes das requisições HTTP (GET e POST) da API, recomendamos o uso do [Postman](https://www.postman.com/), uma ferramenta gráfica muito popular para testar APIs REST.

### Como usar:

1. **Baixe e instale o Postman**: [https://www.postman.com/downloads/](https://www.postman.com/downloads/)

2. **Configurar requisições:**

- Para gerar um QR Code (GET):
  - Método: GET
  - URL: `http://localhost:8081/qrcode/{texto}`  
    *(substitua `{texto}` pelo texto que deseja converter)*

- Para salvar um QR Code no banco (POST):
  - Método: POST
  - URL: `http://localhost:8081/qrcode/save/{texto}`  
    *(substitua `{texto}` pelo texto desejado)*

- Para baixar um QR Code salvo (GET):
  - Método: GET
  - URL: `http://localhost:8081/qrcode/download/{qrCodeId}`  
    *(substitua `{qrCodeId}` pelo ID do QR Code salvo)*

3. **Enviar requisição e visualizar resposta** com o QR Code gerado.

---


📌 Funcionalidades
✅ Geração de QR Code a partir de texto

✅ Armazenamento da imagem no banco de dados PostgreSQL

✅ Download da imagem gerada

✅ API REST com Spring Boot

✅ Containerização com Docker
