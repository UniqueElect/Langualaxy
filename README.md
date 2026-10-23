# Langualaxy

![Logo](langualaxy%20front/src/assets/langualaxy_logo.jpeg)

A language-learning web app: learners take multiple-choice tests, practise vocabulary with picture flashcards grouped by theme, and download lesson materials. Built as my university diploma project; now being developed further as a portfolio project.

- **Backend:** REST API on Spring Boot (this README's main focus)
- **Frontend:** Vue 3 single-page app

## Tech stack

| Layer | Technologies |
| --- | --- |
| Backend | Java 17, Spring Boot 3.2, Spring Web, Spring Data JPA / Hibernate, Maven |
| Database | MariaDB |
| Frontend | Vue 3, Vue Router, Bootstrap 5 |

## Features

- **Tests.** Create a complete test with questions and answer options in a single request. Each attempt draws up to 10 random questions from the test.
- **Flashcards.** Picture cards with a word, grouped by theme. Uploading a card with a new theme creates the theme automatically.
- **Lessons.** Upload, download and rename study files.
- **Themes.** Manage the themes that group flashcards.

## Project structure

```
langualaxy/            Spring Boot backend
  src/main/java/com/langualaxy/
    Controller/        REST controllers
    Service/           business logic
    Repository/        Spring Data JPA repositories
    Entity/            JPA entities
    Dtos/, Request/    response and request models
langualaxy front/      Vue 3 frontend
```

## Getting started

### Prerequisites

- JDK 17+
- Node.js 18+
- MariaDB, or Docker to run it in a container

### 1. Start the database

```bash
docker run --name langualaxy-db \
  -e MARIADB_ROOT_PASSWORD=change-me \
  -e MARIADB_DATABASE=langualaxy \
  -p 3306:3306 \
  -v langualaxy-data:/var/lib/mysql \
  -d mariadb:11
```

### 2. Configure and run the backend

```bash
cd langualaxy
cp .env.example .env    # then fill in the values
./mvnw spring-boot:run  # on Windows: mvnw.cmd spring-boot:run
```

| Variable | Example |
| --- | --- |
| `DB_URL` | `jdbc:mariadb://localhost:3306/langualaxy?useUnicode=true&characterEncoding=utf8` |
| `DB_PASSWORD` | the root password you set for MariaDB |

The API starts on `http://localhost:8080`. Tables are created automatically on first run.

### 3. Run the frontend

```bash
cd "langualaxy front"
npm install
npm run serve
```

Start the backend first: the Vue dev server then runs on `http://localhost:8081`, which is the origin the backend allows for CORS.

## API overview

| Resource | Base path | Notes |
| --- | --- | --- |
| Tests | `/api/tests` | `GET /api/tests/{id}/start` returns up to 10 random questions |
| Questions | `/api/questions` | |
| Answer options | `/api/test-options` | `GET /api/test-options/question/{questionId}` |
| Flashcards | `/api/flashcards` | created via multipart upload: theme, word, image |
| Themes | `/api/themes` | |
| Files | `/api/files` | multipart upload, download by id |
