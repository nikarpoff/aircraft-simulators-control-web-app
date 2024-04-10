# aircraft-simulators-control-web-app
Этот проект разработан в рамках курсовой работой по дисциплине "Проектирование программных систем" и представляет собой веб-приложение для контроля состояния симуляторов летательных аппаратов.

----------
### Обзор проекта
Цель данного проекта - продемонстрировать работу сервера на Spring Boot и клиента на React, развернутых в Docker.

В качестве симуляторов используются консольные приложения Spring, которые, являясь потребителями (consumer), предоставляют случайно сгенерированную информацию о себе (связанную, собственно, с симуляторами летательных аппаратов) запрашивающей стороне - серверу (producer) через брокер сообщений Kafka.

Сервер сохраняет полученные данные в отформатированном виде в базу данных PostgreSQL. Клиентское приложение может запросить у сервера эти данные (по http) и отобразить у себя.

Так реализовано взаимодействие между всеми программными компонентами.

Проект поддерживает базовую http аутентификацию, протокол SSL.

### Используемые технологии
- Backend: Spring
- Frontend: React
- База данных: PostgreSQL
- Контейнеризация: Docker
- Брокер сообщений: Apache Kafka

### Развертывание
Клонируйте репозиторий:
```bash
git clone https://github.com/nikarpoff/aircraft-simulators-control-web-app.git
```
Перейдите в директорию проекта:
```bash
cd aircraft-simulators-control-web-app
```

Соберите и запустите контейнеры Docker:
```bash
docker-compose up --build
```

Клиентское приложение будет доступно по адресу:
```bash
http://localhost:3000
```

По умолчанию, веб-приложение будет подписано на сбор информации сразу у нескольких симуляторов, однако будут развернуты и те симуляторы, которые ещё не отслеживаются сервером. Попробовать их добавить можно вручную, указав модель симулятора:

### Участники
- **nikarpoff**: Карпов Никита *(tg: @karpoffN)*
