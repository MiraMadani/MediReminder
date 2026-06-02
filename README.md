Markdown
# MediReminder API 💊

Веб-додаток для керування нагадуваннями про прийом ліків. Проєкт побудований на базі Spring Boot, використовує базу даних PostgreSQL та повністю контейнеризований за допомогою Docker.

🚀 **Живе посилання на розгорнутий API:** [https://medireminder-api.onrender.com](https://medireminder-api.onrender.com)

---

## 🛠 Технологічний стек
* **Backend:** Java 17, Spring Boot 3.3.0 (Spring Data JPA, Spring Web)
* **Database:** PostgreSQL
* **Containerization & Deployment:** Docker (Multi-stage build), Render Cloud

---

## 🚀 Інструкція із запуску проєкту

### 1. Локальний запуск через Docker (найпростіший спосіб)
Для запуску додатка разом із базою даних у контейнерах, переконайтеся, що у вас встановлено **Docker Desktop**, та виконайте команду в кореневій папці проєкту:

```bash
docker compose up --build
Після цього додаток автоматично збереться та запуститься на порту 8081.

2. Локальний запуск через Maven (Development mode)
Якщо ви хочете запустити проєкт локально без Docker, налаштуйте параметри підключення до вашої локальної бази даних PostgreSQL у файлі src/main/resources/application.properties (або через змінні середовища) та виконайте:

Bash
./mvnw spring-boot:run
🌐 Налаштування деплою (Production)
Проєкт налаштовано для автоматичного CI/CD деплою на платформу Render.

Збірка здійснюється через оптимізований Dockerfile з використанням мульти-етапності (Multi-stage build), що дозволяє кешувати залежності Maven та мінімізувати розмір фінального Production-образу:

Build Stage: Компіляція коду та збірка .jar файлу за допомогою maven:3.9-eclipse-temurin.

Run Stage: Запуск готового додатка на базі легковаго образу eclipse-temurin:17-jre-alpine.

Для безпеки всі конфіденційні дані (паролі та URL бази даних) винесені в змінні середовища:

SPRING_DATASOURCE_URL

SPRING_DATASOURCE_USERNAME

SPRING_DATASOURCE_PASSWORD
