CREATE DATABASE dbCarro
GO
USE dbCarro
GO
CREATE TABLE carros (
placa VARCHAR(20) NOT NULL PRIMARY KEY,
marca VARCHAR(50) NOT NULL,
modelo VARCHAR(50) NOT NULL,
ano INT NOT NULL,
cor VARCHAR(50) NOT NULL
)

SELECT * FROM carros
SELECT placa, marca, modelo, ano, cor FROM carros