# Foro Hub API 

Esta API REST proporciona servicios para gestionar usuarios, cursos,  tópicos respuestas y en una plataforma de Foro Hub. 

# Funcionalidades 

- Usuarios:
  Crear, actualizar y obtener detalles de usuarios.
- Cursos:
  Crear, actualizar y obtener detalles de cursos.
- Tópicos:
  Crear, actualizar, obtener detalles y eliminar tópicos.
- Respuestas:
  Crear, actualizar y obtener detalles de respuestas a tópicos.

# Tecnologías

 -Java 21
 -DevTools para desarrollo
 -Spring Boot
 -Spring Data JPA
 -Spring Security
 -Spring Validation
 -Spring Web
 -Spring Boot Test y Spring Security Test para pruebas
 -Flyway para migraciones de base de datos
 -MySQL Connector/J
 -Lombok para generación automática de código
 -Auth0 Java JWT para manejo de tokens 
 -Springdoc OpenAPI para documentación de API con Swagger UI

# Requisitos Previos

- [x] Intellij Idea Community
- [x] Java JDK 21
- [x] MySQL
- [x] Maven
- [x] Insomnia o Postman

 # Configuración 

 ![Descripcion_Forohub](https://github.com/user-attachments/assets/ca4affd2-0eb4-4c96-8ed3-6c84f622d3d0)

 1. Configuración de la Base de Datos:
    Crear una base de datos en MySQL llamada Forohub_db.
    
 2. Ajustar las configuraciones en el archivo application.yaml para personalizar.
 
 3. Ejecutar la aplicacion en tu Intellij Idea Community
 
 4. Acceder a Swagger UI para explorar y testear los endpoints de la API via web:
    http://localhost:8080/swagger-ui.html

 ![Endpoints_API](https://github.com/user-attachments/assets/1071e71b-590e-4637-a647-da3f00028262)

 5. Alternativamente se puede verificar los endpoints por Insommnia o Postman

    
