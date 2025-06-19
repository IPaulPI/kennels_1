-- Инициализация базы данных для питомника

-- Основные таблицы
CREATE TABLE IF NOT EXISTS Pets (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(50) NOT NULL,
    Type ENUM('Dog', 'Cat', 'Hamster') NOT NULL,
    BirthDate DATE NOT NULL,
    Commands TEXT
);

CREATE TABLE IF NOT EXISTS PackAnimals (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(50) NOT NULL,
    Type ENUM('Horse', 'Camel', 'Donkey') NOT NULL,
    BirthDate DATE NOT NULL,
    Commands TEXT
);

-- Производные таблицы
CREATE TABLE IF NOT EXISTS HorsesAndDonkeys AS
SELECT * FROM PackAnimals WHERE Type IN ('Horse', 'Donkey');

CREATE TABLE IF NOT EXISTS AllAnimals AS
SELECT ID, Name, Type, BirthDate, Commands, 'Pets' AS Source FROM Pets
UNION ALL
SELECT ID, Name, Type, BirthDate, Commands, 'PackAnimals' AS Source FROM PackAnimals;

-- Тестовые данные
INSERT INTO Pets (Name, Type, BirthDate, Commands) VALUES
('Fido', 'Dog', '2020-01-01', 'Sit, Stay, Fetch'),
('Whiskers', 'Cat', '2019-05-15', 'Sit, Pounce'),
('Hammy', 'Hamster', '2021-03-10', 'Roll, Hide');

INSERT INTO PackAnimals (Name, Type, BirthDate, Commands) VALUES
('Thunder', 'Horse', '2015-07-21', 'Trot, Canter, Gallop'),
('Sandy', 'Camel', '2016-11-03', 'Walk, Carry Load'),
('Eeyore', 'Donkey', '2017-09-18', 'Walk, Carry Load, Bray');
