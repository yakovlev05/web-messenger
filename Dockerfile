# Этап сборки
FROM maven:3.9.8-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Копируем файлы проекта и зависимости
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Копируем исходный код проекта и собираем его
COPY src ./src
RUN mvn clean package -DskipTests

# Этап выполнения
FROM openjdk:21-slim
WORKDIR /app

# Копируем собранный JAR файл из этапа сборки
COPY --from=build /app/target/*.jar app.jar

# Указываем команду запуска
ENTRYPOINT ["java", "-jar", "app.jar"]

# Открываем порт, на котором будет работать приложение
EXPOSE 8080
