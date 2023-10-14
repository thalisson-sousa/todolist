# todolist
# API CRUD Feita em Java com Spring Boot

#Funcionalidades
- Castrar Usuario 
- Cadastrar Tarefa
- Recuperar Tarefa
- Atualizar Tarefa
- Autenticação de Usuario
- Validação de Permissão para alterar tarefa
- Validação impedindo recadastro de usuario
- Validação de limite de caracteres no titulo (Limite 50 caracters).

#Tecnologias
- Spring Boot
- Maven
- Banco de Dados em Memoria H2H

#EndPoints
- Cadastrar novo Usuario:
https://todolist-java-lx66.onrender.com/users/

- Cadastrar nova Tarefa:
- https://todolist-java-lx66.onrender.com/users/

#Como usar:
- Primeiro cadastre um novo Usuario passando um Json com (name, username, password)
example:
![image](https://github.com/thalisson-sousa/todolist/assets/27894150/3e75b7dc-008f-4068-8aeb-b7ba2f6deb69)

- Para criar uma tarefa voce deve fazer uma autenticação do tipo Basic Auth, Logo com o usuario criado anteriormente:
 ![image](https://github.com/thalisson-sousa/todolist/assets/27894150/fb0fe9c9-7f3c-4d69-9b28-3710feafa957)

- Deve passar um body com as demais informações:
 ![image](https://github.com/thalisson-sousa/todolist/assets/27894150/b0b78dfd-9395-4b93-be7d-6c7ce739564f)
