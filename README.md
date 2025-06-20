# kennels_1
# 🏡 Питомник домашних и вьючных животных

![Java](https://img.shields.io/badge/Java-17%2B-blue)
![MySQL](https://img.shields.io/badge/MySQL-8.0-orange)
![License](https://img.shields.io/badge/License-MIT-green)

Система учета животных в питомнике с возможностью:
- 📝 Ведения реестра животных
- 🐕 Управления домашними животными (собаки, кошки, хомяки)
- 🐎 Управления вьючными животными (лошади, верблюды, ослы)
- 📊 Генерации отчетов

## 📌 Содержание
- [Установка](#-установка)
- [Запуск](#-запуск)
- [Функционал](#-функционал)
- [Структура проекта](#-структура-проекта)
- [Архитектура](#-архитектура)
- [Разработчикам](#-разработчикам)
- [Лицензия](#-лицензия)

## 🛠 Установка

## 🚀 Запуск
	Соберите и запустите проект:
		mvn clean package
		java -jar target/kennels-registry-1.0-SNAPSHOT-jar-with-dependencies.jar

## 🎮 Функционал
	Главное меню:
		1. Добавить животное
		2. Просмотреть команды
		3. Обучить команде
		4. Список по дате рождения
		5. Лошади и ослы
		6. Животные 1-3 лет
		7. Общее количество
		0. Выход
## 🗂 Структура проекта
	kennels_1/
	├── src/
	│   ├── main/
	│   │   ├── java/com/kennels/
	│   │   │   ├── Animal.java
	│   │   │   ├── Pets/
	│   │   │   ├── PackAnimals/
	│   │   │   ├── DatabaseHandler.java
	│   │   │   └── Main.java
	│   │   └── resources/
	│   │       └── init.sql
	├── scripts/
	│   └── setup.sh
	└── pom.xml
## 👨‍💻 Разработчикам
	* Сборка
		mvn clean install
	* Тестирование
		mvn test
## 📜 Лицензия
	Проект распространяется под лицензией MIT.
## 🏗 Архитектура
	Система использует трехуровневую архитектуру:
		- Модель (Animal, Pets, PackAnimals)
		- Контроллер (DatabaseHandler)
		- Представление (Console UI)

💡 Совет: Для работы с базой данных рекомендуется использовать MySQL Workbench или DBeaver.

🐛 Сообщения об ошибках: При обнаружении проблем создавайте issue в репозитории проекта.
