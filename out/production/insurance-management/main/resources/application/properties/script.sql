-- ==============================================
-- Création de la base
-- ==============================================
CREATE DATABASE IF NOT EXISTS AssuranceDB;
USE AssuranceDB;

-- ==============================================
-- Table Conseiller
-- ==============================================
CREATE TABLE Advisor (
    id VARCHAR(200) PRIMARY KEY,
    firstname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL
);

-- ==============================================
-- Table Client
-- Chaque client est lié à un conseiller
-- ==============================================
 CREATE TABLE Client (
    id CHAR(200) PRIMARY KEY,
    firstname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    idAdvisor CHAR(200) NULL,
    CONSTRAINT fk_client_advisor
         FOREIGN KEY (idAdvisor) REFERENCES Advisor(id)
         ON DELETE SET NULL
         ON UPDATE CASCADE
 );

-- ==============================================
-- Table Contract
-- Chaque contrat est lié à un client
-- ==============================================

CREATE TABLE Contract (
    id VARCHAR(200) PRIMARY KEY,
    type ENUM('AUTOMOBILE', 'REAL_ESTATE', 'HEALTH'),
    startDate DATE,
    endDate DATE,
    idClient CHAR(200) NULL,
    CONSTRAINT fk_contract_client FOREIGN KEY (idClient) REFERENCES Client(id)
        ON DELETE SET NULL
        ON UPDATE CASCADE
);

-- ==============================================
-- Table Claim
-- Chaque sinistre est lié à un contrat
-- ==============================================

CREATE TABLE Claim (
    id VARCHAR(200) PRIMARY KEY,
    type ENUM('CAR_ACCIDENT', 'HOUSE_ACCIDENT', 'ILLNESS') NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    description TEXT,
    startDate DATE NOT NULL,
    endDate DATE NOT NULL,
    idContract VARCHAR(200),
    CONSTRAINT fk_claim_contract FOREIGN KEY (idContract)
        REFERENCES Contract(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);




