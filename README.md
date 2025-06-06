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
- **Next.js**

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

------------------------------------------------------------------------------------------------------------------------------------------------------------

![image](https://github.com/user-attachments/assets/64311173-1d0b-414a-a1ac-ac39ff6e21d2)


---

## Front-end com Next.js

O front-end da aplicação foi desenvolvido com **Next.js**, um framework baseado em **React**, que facilita a criação de interfaces modernas, responsivas e com ótimo desempenho. Utilizamos **componentes reutilizáveis**, chamadas à API usando **Axios**, e estilização com **CSS Modules**.

### Estrutura principal - Front end

A tela inicial (`pages/index.js`) é o coração da aplicação. Lá, conseguimos:

* Listar todos os **Objetivos**
* Criar novos objetivos usando um formulário modal
* Editar e excluir objetivos
* Alterar a porcentagem de conclusão com um **slider**
* Acessar a página de detalhes do objetivo

Exemplo de estrutura:

```plaintext
pages/
├── index.js               # Página inicial com lista de objetivos
├── objetivos/[id].js      # Página de detalhes de um objetivo
components/
├── ObjectiveForm.js       # Formulário usado para criar/editar objetivos
services/
└── api.js                 # Funções que consomem a API Java (Spring)
```


Assim, o front consome diretamente os dados da API REST criada no Spring, mantendo a separação entre as camadas.

---

### Atualização da interface

A UI é atualizada automaticamente após cada ação (criar, editar, deletar ou mudar porcentagem). Para isso, usamos:

```js
useEffect(() => {
  fetchObjectives();
}, []);
```

E em cada ação:

```js
await fetchObjectives();
```

Isso garante que a tela sempre mostre os dados atualizados do banco.

---

### Estilização

Usamos **CSS Modules** para manter o escopo de estilos restrito a cada componente, evitando conflitos globais e mantendo o visual organizado e moderno.

---

## Sobre OKRs

**OKRs** são uma metodologia de gestão de metas baseada em **Objetivos** (qualitativos / metas) e **Resultados-Chave** (quantitativos). Ela serve para tornar possível alinhar metas de forma transparente e mensurável em equipes e empresas. Este sistema simula esse processo, permitindo visualizar o progresso e subdividir ações em iniciativas e tarefas.

---

## Autores

Desenvolvido por **Magno Jr. | 10439896, Rafael |10418331, Vitor | 10438932**
Projeto acadêmico/profissional com foco em **Java, Spring Boot e Arquitetura Limpa**.

---

