CREATE TABLE cursos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    categoria ENUM('FRONTEND_REACT', 'BACKEND_JAVA', 'SPRING_BOOT', 'ARTIFICIAL_INTELLIGENCE', 'PERSONAL_DEVELOPMENT') NOT NULL,
    PRIMARY KEY (id)
);
