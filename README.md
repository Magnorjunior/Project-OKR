# Project-OKR

Este projeto tem como objetivo a criação de um **sistema simplificado de gestão de OKRs** (Objectives and Key Results), voltado para controle estratégico de metas, iniciativas e resultados, exemplificado aqui para uso no setor **comercial** de uma empresa.

## Objetivo

Fornecer uma ferramenta para **registrar, monitorar e atualizar metas** de negócio, suas iniciativas e resultados esperados, facilitando a gestão de objetivos da empresa por meio de OKRs.

---

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.4.5**
- **Spring Data JPA**
- **PostgreSQL**
- **Lombok**
- **Maven**

## Modelo de Entidades

```plaintext
Objective
├── KeyResult
│   └── Initiative
│       └── Task (Lista embutida)

```

**Funcionalidades Principais**
CRUD completo de Objectives, Key Results e Initiatives.

Atualização automática da porcentagem de conclusão de um objetivo com base em suas metas e iniciativas.

Registro de Tasks (tarefas) dentro das Iniciativas, com possibilidade de marcar como concluída ou não.

API REST pronta para integração com frontend ou ferramentas externas.

## Funcionalidades Principais

* CRUD completo de **Objectives**, **Key Results** e **Initiatives**.
* Atualização automática da **porcentagem de conclusão** de um objetivo com base em suas metas e iniciativas.
* Registro de **Tasks (tarefas)** dentro das Iniciativas, com possibilidade de marcar como concluída ou não.
* API REST pronta para integração com frontend ou ferramentas externas.

## Exemplo de Cadastro via JSON

### Cadastro de um Objective

```json
{
  "titulo": "Aumentar as Vendas",
  "descricao": "Expandir a presença no mercado no próximo trimestre"
}
```

### Cadastro de um Key Result

```json
{
  "descricao": "Alcançar R$ 100.000,00 em vendas",
  "meta": "100000",
  "objectiveId": 1
}
```

### Cadastro de uma Initiative com tarefas

```json
{
  "titulo": "Campanha de Marketing Digital",
  "descricao": "Realizar anúncios nas redes sociais",
  "porcentagemConclusao": 40.0,
  "keyResultId": 1,
  "tasks": [
    { "descricao": "Criar artes visuais", "concluida": true },
    { "descricao": "Configurar campanha no Instagram", "concluida": false }
  ]
}
```

---

## Como Executar o Projeto

1. Clone este repositório:

   ```bash
   git clone https://github.com/seu-usuario/seu-repositorio.git
   ```

2. Configure seu `application.properties` com os dados do seu banco PostgreSQL:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/okrdb
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   ```

3. Compile e rode a aplicação no VS Code

4. A API estará disponível em:

   ```
   http://localhost:8080/
   ```

---

## Prints do Sistema como exemplo:

![image](https://github.com/user-attachments/assets/1869ee61-6015-4be8-a43d-a3afea90ab6f)

---

## Sobre OKRs

**OKRs** são uma metodologia de gestão de metas baseada em **Objetivos** (qualitativos) e **Resultados-Chave** (quantitativos). Com ela, é possível alinhar metas de forma transparente e mensurável em equipes e empresas. Este sistema simula esse processo, permitindo visualizar o progresso e subdividir ações em iniciativas e tarefas.

---

## Autores

Desenvolvido por **Magno Jr. | 10439896, Rafael, Vitor | 10438932**
Projeto acadêmico/profissional com foco em **Java, Spring Boot e Arquitetura Limpa**.

---

