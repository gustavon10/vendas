# Sistema de Vendas

Este é um sistema de vendas desenvolvido em Java com Spring Boot, utilizando o banco de dados em memória H2. O projeto está configurado para ser executado em um ambiente Docker.

---

## 🛠️ Tecnologias Utilizadas
- **Java 17**
- **Spring Boot 3.1.2**
- **H2 Database**
- **Maven**
- **Docker**

---

## 🚀 Como Rodar o Projeto

### Pré-requisitos
Certifique-se de ter o **Docker** instalado no seu sistema.

### Passo a Passo

1. **Clone o repositório**
   ```bash
   git clone https://github.com/gustavon10/vendas.git
   cd vendas
   ```

2. **Construa a imagem Docker**
    ```bash
    docker build -t vendas .
    ```

3. **Execute o contêiner Docker**
   ```bash
   docker run -p 8080:8080 vendas-app
   ```

4. **Teste o sistema**
    - API: Use o Postman ou outra ferramenta para testar as rotas da API.
    - H2 Console: Acesse o console do banco de dados H2 no navegador:
        - URL: http://localhost:8080/h2-console
        - JDBC URL: jdbc:h2:mem:testdb
        - User Name: sa
        - Password: password

5. **Parar o container**

    Para parar o container, pressione `Ctrl+C` no terminal ou use:
    ```bash
    docker container ls
    docker container stop <CONTAINER_ID>
    ```
