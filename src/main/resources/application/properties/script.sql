-- ==============================================
-- Création de la base
-- ==============================================
CREATE DATABASE IF NOT EXISTS AssuranceDB;
USE AssuranceDB;

-- ==============================================
-- Table Conseiller
-- ==============================================
CREATE TABLE Advisor (
    id VARCHAR PRIMARY KEY,
    firstname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL
);

-- ==============================================
-- Table Client
-- Chaque client est lié à un conseiller (optionnel)
-- ==============================================
CREATE TABLE Client (
    id INT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    idConseiller INT,
    FOREIGN KEY (id) REFERENCES Conseiller(id)
        ON DELETE SET NULL
        ON UPDATE CASCADE
);

-- ==============================================
-- Données de test (optionnel)
-- ==============================================
--INSERT INTO Conseiller (nom, prenom, email) VALUES
--('Dupont', 'Jean', 'jean.dupont@assurance.com'),
--('Martin', 'Sophie', 'sophie.martin@assurance.com');

--INSERT INTO Client (nom, prenom, email, idConseiller) VALUES
--('Durand', 'Paul', 'paul.durand@gmail.com', 1),
--('Lefevre', 'Claire', 'claire.lefevre@gmail.com', 1),
--('Bernard', 'Luc', 'luc.bernard@gmail.com', 2);
