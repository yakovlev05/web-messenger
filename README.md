# Простой web-messenger 📨

Web-messenger на Spring + React

- Сайт — [web-messenger.yakovlev05.ru](https://web-messenger.yakovlev05.ru/)
- Документация по API - [web-messenger.yakovlev05.ru/swagger-ui](https://web-messenger.yakovlev05.ru/swagger-ui/index.html)

# О проекте 🗒️

Проект был выполнен в рамках знакомства 📖 со Spring. Основная цель 🎯 — научиться создавать REST приложения на spring. В данном проекте frontend часть не играет особой роли и делалась для целостной картины 🏁.

# Стек технологий 🕹️

## Backend <img width="30" src="https://user-images.githubusercontent.com/25181517/117201470-f6d56780-adec-11eb-8f7c-e70e376cfd07.png" alt="Spring" title="Spring"/>

На бэкенде используется Spring Boot. Применена луковичная архитектура 🧅:
- Repository - работа с данными 💾
- Service - бизнес-логика 💡
- DTO и Entity - передача данных 💬
- Controller - REST API 📃

Настроена авторизация 🔏 при помощи JWT, в проекте используются Websocket (STOMP) подключение 🔗.

## Frontend <img width="30" src="https://github-production-user-asset-6210df.s3.amazonaws.com/62091613/261395532-b40892ef-efb8-4b0e-a6b5-d1cfc2f3fc35.png" alt="Vite" title="Vite"/> + <img width="30" src="https://user-images.githubusercontent.com/25181517/183897015-94a058a6-b86e-4e42-a37f-bf92061753e5.png" alt="React" title="React"/>

Фронтенд был создан для завершённости проекта и удобного взаимодействия / тестирования ✨.
Используются Vite + React TS. Весь фронтенд построен на использовании 🖌️ компонентов от [Ant Design](https://ant.design/). Также для стилизации 💄 попробовал поработать с [tailwindcss](https://tailwindcss.com/).

# Про .env файл ⚙️

## Настройки базы данных PostgreSQL <img width="30" src="https://user-images.githubusercontent.com/25181517/117208740-bfb78400-adf5-11eb-97bb-09072b6bedfc.png" alt="PostgreSQL" title="PostgreSQL"/>

- **`POSTGRES_DB`** - название бд
- **`POSTGRES_USER`** - имя пользователя
- **`POSTGRES_PASSWORD`** - пароль пользователя
- **`SPRING_DATASOURCE_URL`** - подключение к jdbc (пример: `jdbc:postgresql://localhost:5432/mydata`)

## JWT аутентификация 🔏

- **`JWT_SECRET_KEY`** - ключ 
- **`JWT_TOKEN_LIFE_TIME_IN_MS`** - время жизни access токена в миллисекундах
- **`JWT_REFRESH_TOKEN_LIFE_TIME_IN_MS`** - время жизни refresh токена в миллисекундах

## Другое ℹ️

- **`WEBSOCKET_ALLOWED_ORIGINS`** - Хосты, с которых можно подключиться к webscoket (пример: `http://localhost,https://example.com`)


# Как запустить <img width="30" src="https://user-images.githubusercontent.com/25181517/117207330-263ba280-adf4-11eb-9b97-0ac5b40bc3be.png" alt="Docker" title="Docker"/>

```
System.out.println(❤️==🐋);
true
```

Весь сервис разворачивается через докер:

- Клонируем репозиторий 📩
```
git clone https://github.com/yakovlev05/web-messenger.git
```

- Переходим в директорию 📂
```
cd web-messenger
```

- Запускаем docker 🐳
```
docker compose up -d
```

‼️Обратите внимание, что фронтенд (сервис `web-messenger-frontend`) поднимается в виде сервера для разработки ⚒️ (то есть не собирается в статические файлы 🗃️). Чтобы это изменить поменяйте в docker-compose.yml `target: dev` на `target: prod` и порт в nginx.conf с 5173 на 80.
