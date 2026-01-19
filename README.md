# Apollum

Simply put, I had to deep dive into JPA and Spring Data.

### Inspiration

- Old school project refactored from a CLI tool in C++ to Springboot REST endpoints. (Plus some added features and entity mapping).

### Installation and Running

- Clone the repo.
- Configure a postgres instance and update the details in application.yml file.
  - Auto-build the tables in postgres update the `spring.jpa.hibernate.ddl-auto=create`
  - To pre-populate the data in data.sql, update `spring.sql.init.mode=always`
- Finally just run the project with `mvn spring-boot:run` and visit http://localhost:8080/ to view the swagger.