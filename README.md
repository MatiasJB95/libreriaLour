# Tienda de Libros API REST en Java con Spring Boot

Esta API REST en Java con Spring Boot es una tienda de libros que permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en libros y categorías, así como administrar un carrito de compras. También incluye una vista de destacados que muestra una lista de libros destacados y/o en oferta.

## Funcionalidades

- Creación de libros: Permite agregar nuevos libros a la base de datos especificando su título, autor, editorial, precio y categoría.
- Actualización de libros: Permite actualizar la información de un libro existente, como su título, autor, editorial y precio.
- Eliminación de libros: Permite eliminar un libro de la base de datos según su ID.
- Consulta de libros: Permite obtener información detallada de un libro específico o de todos los libros disponibles.
- Creación de categorías: Permite agregar nuevas categorías de libros a la base de datos.
- Consulta de categorías: Permite obtener información de todas las categorías disponibles.
- Filtrado de libros por categoría: Permite obtener una lista de libros que pertenecen a una categoría específica.
- Administración del carrito de compras: Permite agregar productos al carrito, eliminar productos del carrito en cantidad y calcular el costo total de los productos en el carrito.
- Vista de destacados: Permite mostrar una lista de libros destacados o en oferta.

## Tecnologías y habilidades utilizadas

- Java: Lenguaje de programación principal utilizado en el desarrollo de la API REST.
- Spring Boot: Framework de desarrollo de aplicaciones Java que simplifica la creación de aplicaciones basadas en Spring.
- Controladores (Controllers): Se utilizan para manejar las solicitudes HTTP y definir los puntos finales de la API.
- DTO (Data Transfer Objects): Se utilizan para transferir datos entre la capa de controladores y la capa de servicios, asegurando la separación de preocupaciones y evitando la exposición de detalles internos.
- Repositorios (Repositories): Se utilizan para interactuar con la base de datos y realizar operaciones CRUD en los modelos de datos.
- Servicios (Services): Se utilizan para implementar la lógica empresarial de la aplicación, realizar validaciones y orquestar las operaciones en los repositorios.
- Base de datos: Se utiliza una base de datos (por ejemplo, MySQL, PostgreSQL) para almacenar la información de libros, categorías y carritos de compras.
- Operaciones CRUD: Se implementan las operaciones básicas de Crear, Leer, Actualizar y Eliminar en libros y categorías.
- Filtrado de datos: Se implementa la funcionalidad de filtrar libros por categoría para mostrar solo los libros relevantes.
- Gestión de carrito de compras: Se permite agregar productos al carrito, eliminar productos del carrito en cantidad y calcular el costo total de los productos en el carrito.
- Vista de destacados: Se muestra una lista de libros destacados u en oferta para resaltar ciertos productos.

## Demo

https://github.com/MatiasJB95/libreriaLour/assets/115195236/8995394b-124a-40fd-9ea9-6b28af97fab7

## Configuración y ejecución

Para configurar y ejecutar la aplicación:

1. Clona el repositorio en tu máquina local.
2. Configura la base de datos según la base de datos de tu elección.
3. Actualiza la configuración de la base de datos en el archivo de configuración de la aplicación (`application.properties` o `application.yml`).
4. Ejecuta la aplicación utilizando tu IDE preferido o mediante la línea de comandos.
5. Realiza las solicitudes HTTP correspondientes utilizando herramientas como Postman o cURL.


## 🔗 Links
[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://app.aluracursos.com/emprega-one/profile/matiasss95)
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/matiasjb95/)



