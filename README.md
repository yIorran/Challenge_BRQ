# Challenge_BRQ - Mercadinho do Zé

## Used technologies: 
- Maven (lastest version).
- Java 16.0.2 (you also can use Java 11.0.12, everything works as expected with these two versions).
- Spring-boot 2.5.5.
- Postman (lastest version).
- Lombok
<br>
And more.. for more information about technologies and dependencies look into pom.xml
<br>
This project has the objective to pushing my knowledge to the limits, develop my abilities with Clean Architecture and get better on writing clean and easy-changing codes,
improving and refactoring codes easily are one of the biggest dificulties that developers have today, doing it since the begining of your career must have a long term advantage
this is why I am developing this code mentored by: @kevingabrielmelo and @CaioHBastos.
<br>

### Business rules: 
- A category cannot be excluded if there is a product linked to it.
- While adding a new category, we must inform only the "nome"
- While adding a new product, we must inform: "nome", "descricao", "quantidade", "preco" and "categoria"{"id"}.
- When adding a new product, the attributes "ofertado", "ativo" and "porcentagem" are setted with default values, being: <br>"ativo":true, <br>"ofertado":false <br>and "porcentagem":0
- While updating a full procuct using PUT verb, we must inform the procuct ID on PathVariable and in the body inform: "nome", "descricao", "quantidade", "preco", "ofertado", "ativo", "porcentagem" and "categoria"{"id"} (this last one just if necessary).
- You can also update a product partially using the PATCH verb, so you just inform the attributes you are going to change.
