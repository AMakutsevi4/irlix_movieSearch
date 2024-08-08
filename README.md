# Обзор

RESTful приложение для управления подборкой кино. 
Предоставляет функциональность для работы с жанрами, фильмами, критиками и рецензиями.

# Функционал

### Жанры
- CRUD

**Endpoints**
- `POST api/genres` - Создать новый жанр
- `GET api/genres` - Получить все жанры
- `GET api/genres/{id}` - Получить жанр по ID
- `PUT api/genres/{id}` - Обновить жанр по ID
- `DELETE api/genres/{id}` - Удалить жанр по ID

### Фильмы
- CRUD
- Получение фильмов по жанрам (учитывается, что их может быть несколько)
- Пагинация списка фильмов

**Endpoints**
- `POST api/movies` - Создать новый фильм
- `GET api/movies` - Получить все фильмы (поддерживает пагинацию)
- `GET api/movies/{id}` - Получить фильм по ID
- `PUT api/movies/{id}` - Обновить фильм по ID
- `DELETE api/movies/{id}` - Удалить фильм по ID
- `GET api/movies/by-genre/{genre_id}` - Получить фильмы по жанру

### Критики
- CRUD

**Endpoints**
- `POST api/critics` - Создать нового критика
- `GET api/critics` - Получить всех критиков
- `GET api/critics/{id}` - Получить критика по ID
- `PUT api/critics/{id}` - Обновить критика по ID
- `DELETE api/critics/{id}` - Удалить критика по ID

### Рецензии
- CRUD
- Получение рецензий критиков по конкретному фильму

**Endpoints**
- `POST api/reviews` - Создать новую рецензию
- `GET api/reviews` - Получить все рецензии
- `GET api/reviews/{id}` - Получить рецензию по ID
- `PUT api/reviews/{id}` - Обновить рецензию по ID
- `DELETE api/reviews/{id}` - Удалить рецензию по ID
- `GET api/reviews/movie/{movie_id}` - Получить рецензии по фильму

# Установка и запуск

1. Клонируйте репозиторий
```bash
git@github.com:AMakutsevi4/irlix_movieSearch.git
```
2. Создайте БД

```bash
create database movie_search
```
3.
Приложение будет доступно по адресу [http://localhost:8080](http://localhost:8080)..

# Endpoints Movie

## Поиск фильмов по имени
**Запрос:**
GET /api/movies/searchName?name={имя_фильма}
**Пример:**
http://localhost:8080/api/movies/searchName?name=Фильм2

## Фильтрация списка фильмов
**Запрос:**
GET /api/movies?srt={порядок_сортировки}&sortBy={поле_сортировки}
**Пример:**
http://localhost:8080/api/movies?srt=asc&sortBy=yearShow
- **srt** - порядок сортировки (`asc` для возрастания, `desc` для убывания).
- **sortBy** - поле для сортировки (по умолчанию `name`).

## Поиск фильмов по году показа
**Запрос:**
GET /api/movies/searchYear?yearShow={год_показа}
**Пример:**
http://localhost:8080/api/movies/searchYear?yearShow=2024

## Поиск фильмов по рейтингу
**Запрос:**
GET /api/movies/searchRating?rating={рейтинг}
**Пример:**
http://localhost:8080/api/movies/searchRating?rating=6.9

## Поиск фильмов по диапазону дат
**Запрос:**
GET /api/movies/searchByYearBetween?startYear={начальный_год}&endYear={конечный_год}
**Пример:**
http://localhost:8080/api/movies/searchByYearBetween?startYear=2023&endYear=2024

## Поиск фильмов по диапазону рейтинга
**Запрос:**
GET /api/movies/searchByRatingBetween?startRating={начальный_рейтинг}&endRating={конечный_рейтинг}
**Пример:**
http://localhost:8080/api/movies/searchByRatingBetween?startRating=6.9&endRating=8.5


# Используемые технологии

## Backend
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
Используется для разработки RESTful сервисов.

## База данных
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
Реляционная база данных для хранения данных.

## Тестирование
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)
Инструмент для тестирования API.

## Документирование
[![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)](http://localhost:8080/swagger-ui/index.html#/)
Используется для автоматического документирования API. [Документация доступна здесь](http://localhost:8080/swagger-ui/index.html#/).