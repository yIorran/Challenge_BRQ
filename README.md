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
  This project has the objective to pushing my knowledge to the limits, develop my abilities with Clean Architecture and
  get better on writing clean and easy-changing codes, improving and refactoring codes easily are one of the biggest
  dificulties that developers have today, doing it since the begining of your career must have a long term advantage and
  this is why I am developing this project mentored by: @kevingabrielmelo and @CaioHBastos.<br>
  This project is a model of supermarket website, having the rules and functionalities below:
  <br>

### Business rules:

- A category cannot be excluded if there is a product linked to it.
- While adding a new category, we must inform only the "nome"
- While adding a new product, we must inform: "nome", "descricao", "quantidade", "preco" and "categoria"{"id"}.
- When adding a new product, the attributes "ofertado", "ativo" and "porcentagem" are setted with default values,
  being: <br>"ativo":true, <br>"ofertado":false <br>and "porcentagem":0
- You can also update a product partially using the PATCH verb, so you just inform the attributes you are going to
  change.

## Routes:

### GET

Listing all categories:

 ``` 
 http://localhost:8079/categorias/
 ```

Finding category by id:

 ```
 http://localhost:8079/categorias/{idCategory}
 ```

Listing all products:

```
 http://localhost:8079/produtos
```

Finding by category name:

```
 http://localhost:8079/produtos?categoria={categoryName}
```

Finding by product's brandName:

```
 http://localhost:8079/produtos?marca={brandName}
```

Finding by product id:

```
 http://localhost:8079/produtos/{id}
```

### DELETE

Deleting by category id:

``` 
http://localhost:8079/categorias/{id}
```

Deleting by product id:

``` 
http://localhost:8079/produtos/{id}
```

### POST

Adding category:

``` 
http://localhost:8079/categorias
```

Adding product:

``` 
http://localhost:8079/produtos
```

![Post](https://user-images.githubusercontent.com/76667034/138158137-36b36f5e-7516-4112-b02a-f7b9684b33d3.PNG)

### PUT

Updating category:

``` 
http://localhost:8079/categorias/{id}
```

### PATCH

Updating product partially:

``` 
http://localhost:8079/produtos/{id}
```

![Patch](https://user-images.githubusercontent.com/76667034/138157840-171b2abc-4481-4ab9-881c-f4f5301e8b67.PNG)

