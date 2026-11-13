# Langualaxy

![Logo](langualaxy%20front/src/assets/langualaxy_logo.jpeg)

A language-learning web app: learners take multiple-choice tests, practise vocabulary with picture flashcards grouped by theme, and download lesson materials. Built as my university diploma project; now being developed further as a portfolio project.

- **Backend:** REST API on Spring Boot (this README's main focus)
- **Frontend:** Vue 3 single-page app

## Features

- **Tests.** Create a test with questions and answer options on the "Create test" page, or with a single API request. Each attempt draws up to 10 random questions, and answers are checked on the server, so correct answers never reach the browser.
- **Flashcards.** Picture cards with a word, grouped by theme. Uploading a card with a new theme creates the theme automatically.
- **Lessons.** Upload, download and rename study files.
- **Themes.** Manage the themes that group flashcards.

## Under the hood

- **DTOs at the API boundary.** Responses are Java records, and create and update endpoints accept request DTOs instead of entities, so a client cannot set ids or relations directly.
- **No N+1 queries.** Starting a test takes three SQL queries no matter how many questions it has: an existence check, the questions, and all their answer options in one batch.
- **Persistence settings.** All `@ManyToOne` associations are lazy, `open-in-view` is disabled, and every service method runs in a transaction (read-only for queries). Deleting a test cascades to its questions and answer options.
- **Light file list.** The lessons list loads only file metadata through a JPQL constructor expression; file contents are read only on download.
- **Consistent errors.** A global `@RestControllerAdvice` returns Problem Details JSON, so a missing resource is a clear 404 instead of an empty response.
- **Constructor injection** throughout, with no field `@Autowired`.

## Tech stack

| Layer | Technologies |
| --- | --- |
| Backend | Java 17, Spring Boot 3.2, Spring Web, Spring Data JPA / Hibernate, Maven |
| Database | MariaDB |
| Frontend | Vue 3, Vue Router, Bootstrap 5 |

## Project structure

```
langualaxy/            Spring Boot backend
  src/main/java/com/langualaxy/
    Controller/        REST controllers
    Service/           business logic and transactions
    Repository/        Spring Data JPA repositories
    Entity/            JPA entities
    Dtos/, Request/    response and request models
    Mappers/           entity <-> DTO mapping
    Exceptions/        NotFoundException and the global error handler
    Configuration/     CORS and security setup
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
cp .env.example .env    # then fill in DB_URL and DB_PASSWORD
./mvnw spring-boot:run  # on Windows: mvnw.cmd spring-boot:run
```

| Variable | Example |
| --- | --- |
| `DB_URL` | `jdbc:mariadb://localhost:3306/langualaxy?useUnicode=true&characterEncoding=utf8` |
| `DB_PASSWORD` | the root password you set for MariaDB |

The other variables in `.env.example` are reserved for authentication, which is not enabled yet. The API starts on `http://localhost:8080`, and tables are created automatically on first run.

### 3. Run the frontend

```bash
cd "langualaxy front"
npm install
npm run serve
```

Start the backend first: the Vue dev server then runs on `http://localhost:8081`, which is the origin the backend allows for CORS.

### 4. Add a test

Open `http://localhost:8081/tests` and click **Create test**. Or send the same data straight to the API:

```bash
curl -X POST http://localhost:8080/api/tests \
  -H "Content-Type: application/json" \
  -d '{
        "name": "Present Simple",
        "questions": [
          {
            "text": "She ___ to school every day.",
            "options": [
              { "text": "go", "correct": false },
              { "text": "goes", "correct": true }
            ]
          }
        ]
      }'
```

## API overview

| Resource | Base path | Notable endpoints |
| --- | --- | --- |
| Tests | `/api/tests` | `POST /api/tests` creates a test with its questions and options; `GET /{id}/start` returns up to 10 random questions without correct answers; `POST /{id}/submit` checks the answers and returns the ids of correctly answered questions |
| Questions | `/api/questions` | create, read and update single questions |
| Answer options | `/api/test-options` | `GET /question/{questionId}` lists the options of a question |
| Flashcards | `/api/flashcards` | `POST /create` is a multipart upload of theme, word and image; `GET /get/{themeId}` lists the cards of a theme |
| Themes | `/api/themes` | |
| Files | `/api/files` | `POST /upload` is a multipart upload; `GET /all` returns metadata only; `GET /{id}` downloads a file |
