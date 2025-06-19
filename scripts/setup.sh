#!/bin/bash

# Установка MySQL
sudo apt update
sudo apt install -y mysql-server

# Настройка БД
sudo mysql -e "CREATE DATABASE IF NOT EXISTS HumanFriends;"
sudo mysql -e "CREATE USER IF NOT EXISTS 'kennel_user'@'localhost' IDENTIFIED BY 'password';"
sudo mysql -e "GRANT ALL PRIVILEGES ON HumanFriends.* TO 'kennel_user'@'localhost';"
sudo mysql -e "FLUSH PRIVILEGES;"

# Инициализация таблиц
sudo mysql HumanFriends < src/main/resources/init.sql

echo "Setup completed!"
