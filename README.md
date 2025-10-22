Expense Tracker App
Uma aplicação completa de controle de despesas desenvolvida com Spring Boot que permite gerenciar categorias e despesas de forma eficiente.

🚀 Tecnologias Utilizadas
Java 21

Spring Boot 3.5.6

Spring Data JPA

MySQL

Maven

Lombok

📋 Funcionalidades
Categorias
✅ Criar nova categoria

✅ Listar todas as categorias

✅ Buscar categoria por ID

✅ Atualizar categoria

✅ Excluir categoria

Despesas

✅ Registrar nova despesa

✅ Listar todas as despesas

✅ Buscar despesa por ID

✅ Atualizar despesa

✅ Excluir despesa

🗄️ Estrutura do Banco de Dados
Tabela: categories
sql
CREATE TABLE categories (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);
Tabela: expenses
sql
CREATE TABLE expenses (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    amount DECIMAL(10,2) NOT NULL,
    expense_date DATE NOT NULL,
    category_id BIGINT NOT NULL,
    FOREIGN KEY (category_id) REFERENCES categories(id)
);
⚙️ Configuração
Pré-requisitos
Java 21

MySQL

Maven

Configuração do Banco de Dados
Crie o banco de dados:

sql
CREATE DATABASE expense_tracker;
Configure as credenciais no application.properties:

properties
spring.datasource.url=jdbc:mysql://localhost:3306/expense_tracker
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
Executando a Aplicação
Clone o repositório:

bash
git clone https://github.com/seu-usuario/expense-tracker-app.git
Navegue até o diretório do projeto:

bash
cd expense-tracker-app
Execute a aplicação:

bash
mvn spring-boot:run
A aplicação estará disponível em: http://localhost:8080


Endpoints Disponíveis
Categorias
Método	Endpoint	Descrição
POST	/api/categories	Criar nova categoria
GET	/api/categories	Listar todas as categorias
GET	/api/categories/{id}	Buscar categoria por ID
PUT	/api/categories/{id}	Atualizar categoria
DELETE	/api/categories/{id}	Excluir categoria
Despesas
Método	Endpoint	Descrição
POST	/api/expenses	Registrar nova despesa
GET	/api/expenses	Listar todas as despesas
GET	/api/expenses/{id}	Buscar despesa por ID
PUT	/api/expenses/{id}	Atualizar despesa
DELETE	/api/expenses/{id}	Excluir despesa
🏗️ Estrutura do Projeto
text
src/main/java/vbonilha/expense/
├── controller/          # Controladores REST
├── service/            # Lógica de negócio
├── repository/         # Camada de acesso a dados
├── entity/            # Entidades JPA
├── dto/               # Objetos de transferência de dados
├── mapper/            # Mapeadores entre entidades e DTOs
└── exceptions/        # Tratamento de exceções
🔧 Exemplos de Uso
Criar Categoria
bash
curl -X POST http://localhost:8080/api/categories \
  -H "Content-Type: application/json" \
  -d '{"name": "Alimentação"}'
Criar Despesa
bash
curl -X POST http://localhost:8080/api/expenses \
  -H "Content-Type: application/json" \
  -d '{
    "amount": 150.50,
    "expenseDate": "2024-01-15",
    "categoryDto": {"id": 1}
  }'
🐛 Tratamento de Erros
A aplicação inclui tratamento global de exceções com respostas padronizadas para:

ResourceNotFoundException - Recurso não encontrado (404)

Exception - Erros internos do servidor (500)

📦 Dependências Principais
spring-boot-starter-data-jpa - Persistência de dados

spring-boot-starter-web - API REST

mysql-connector-j - Driver MySQL

lombok - Redução de boilerplate


👨‍💻 Desenvolvido por
[Vinícius Tomaz Bonilha] - [viniciustomazbonilha@gmail.com]

Nota: Lembre-se de configurar as credenciais do banco de dados no arquivo application.properties antes de executar a aplicação.

