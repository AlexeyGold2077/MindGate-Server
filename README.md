# MindGate

### MindGate is a backend application that provides access to various artificial intelligence models using REST API. Written using Java Maven and Spring Boot.

### Описание конечных точек

#### `/new/user` - создание нового пользователя
##### Обязательные параметры
- `userId` - уникальный числовой идендификатор
- `model` - модель GPT, допускается одна из следующих
  - `DEFAULT`
  - `GPT4`
  - `GPT4O`
  - `GPT4TURBO`

#### `/new/message/user` - отправка сообщения в роли юзера
##### Обязательные параметры
- `userId` - уникальный числовой идендификатор
- `message` - новое сообщение в чате пользователя

#### `/new/message/system` - отправка сообщения в роли системы (используется для словестной настройки бота)
##### Обязательные параметры
- `userId` - уникальный числовой идендификатор
- `message` - новое сообщение в чате пользователя

#### `/get/user/messages` - получение сообщений пользователя
##### Обязательные параметры
- `userId` - уникальный числовой идендификатор

### Пример запросов и ответов

- Создание пользователя
`http://localhost:8080/new/user?id=1&model=GPT4O`

- Отправка сообщения в роли пользователя
`http://localhost:8080/new/message/user?id=1&message=What is Stanley Kubrick's name?`

- Отправка сообщения в роли системы
`http://localhost:8080/new/message/system?id=1&message=You are rude assistant.`

- получение сообщений пользователя
`http://localhost:8080/new/user?id=1`
