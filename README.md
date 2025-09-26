# Insurance Management Console Application

## Overview

This is a Java-based console application designed to manage insurance contracts and claims for an insurance company. The application digitalizes the management of advisors, clients, contracts, and claims, utilizing Java 8 features such as Streams API, Lambda expressions, Method References, Optional, and Java Time API. It follows a layered architecture (Model, Enum, View, Service, DAO) and uses JDBC for database persistence.

## Features

- **Advisor Management**:
    - Add an advisor (auto-generated ID).
    - Delete an advisor by ID.
    - Search for an advisor by ID.
    - Display clients associated with an advisor by advisor ID.
- **Client Management**:
    - Add a client (auto-generated ID).
    - Delete a client by ID.
    - Search for clients by last name, sorted alphabetically (using Streams API).
    - Search for a client by ID (using Optional).
    - Display clients associated with an advisor by advisor ID (using Streams API).
- **Contract Management**:
    - Add a contract (auto-generated ID, linked to a client by client ID).
    - Display contract details by ID (using Optional).
    - Delete a contract by ID.
    - Display contracts subscribed by a client by client ID.
- **Claim Management**:
    - Add a claim (auto-generated ID, linked to a contract by contract ID).
    - Delete a claim by ID.
    - Calculate total claim costs for a client by client ID (using Streams API).
    - Search for a claim by ID (using Optional).
    - Display claims for a contract by contract ID (using Streams API).
    - Display claims sorted by amount in descending order (using Streams API).
    - Display claims for a client by client ID (using Streams API).
    - Display claims before a specified date (using Streams API).
    - Display claims exceeding a specified amount (using Streams API).

## Technical Specifications

- **Language**: Java 8
- **Architecture**: Layered (Model, Enum, View, Service, DAO)
- **Database**: JDBC for persistence
- **Key Features**:
    - Encapsulation with private properties and getters/setters.
    - Use of Streams API for collection processing.
    - Lambda expressions and Method References for cleaner code.
    - Optional for null safety.
    - Java Time API for date management.
- **Dependencies**:
    - JDBC driver (e.g., MySQL or PostgreSQL, depending on the database used).
    - No external frameworks required.

## Prerequisites

- Java Development Kit (JDK) 8
- A relational database (e.g., MySQL, PostgreSQL) with JDBC driver
- Git for version control
- Maven (optional, if using a Maven-based project structure)

## Installation

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/your-username/insurance-management-app.git
   ```

2. **Set Up the Database**:

    - Create a database in your preferred RDBMS (e.g., MySQL or PostgreSQL).
    - Execute the SQL scripts provided in the `sql/` directory to set up the required tables.
    - Update the JDBC connection details (URL, username, password) in the configuration file (e.g., `config.properties` or directly in the DAO layer).

3. **Build the Project**:

4. Alternatively, compile the Java files manually:

   ```bash
   javac -d bin src/**/*.java
   ```


1) **Run the Application**:

    - Execute the JAR file:

      ```bash
      java -jar target/insurance-management-app.jar
      ```

    - Or run the main class directly:

      ```bash
      java -cp bin com.example.Main
      ```

## Usage

1. Launch the application to access the main menu.
2. Navigate through the console menu to manage advisors, clients, contracts, and claims.
3. Follow the prompts to perform CRUD operations or filter/sort data as needed.
4. Example commands:
    - Add a client: Select "Client Management" &gt; "Add a client" and enter the required details.
    - View claims by contract ID: Select "Claim Management" &gt; "Display claims by contract ID" and provide the contract ID.

## Project Structure

```
insurance-management-app/
├── src/
│   ├── main/
│   │   ├── java.com.ismail.insurancemanagement/
│   │   │   ├── config/ 
│   │   │   ├── util/ 
│   │   │   ├── controller/     # ClientController, ContractController, ClaimController ,AdvisoController classes
│   │   │   ├── model/          # Person, Advisor, Client, Contract, Claim classes
│   │   │   ├── enum/           # ContractType, ClaimType enums
│   │   │   ├── view/           # ClientView, ContractView, ClaimView , AdvisorView classes
│   │   │   ├── service/        # CRUD and filter/sort logic
│   │   │   ├── dao/            # JDBC-based data access layer
│   │   │   ├── Main.java       # Application entry point
│   │   ├── resources/
│   │   │   ├── application.properties/ 
│   │   │   ├── util/ 
│   │   │   ├── util/ 
│   ├── test.java.com/
│   ├── sql/                    # Database schema and setup scripts
├── README.md                   # This file
├── pom.xml                     # Maven configuration (if applicable)
├── insurance-management-app.jar # Executable JAR
├── class-diagram.png           # UML class diagram
```

## Class Diagram

The class diagram is available in the `class-diagram.png` file, reflecting the relationships between `Person`, `Advisor`, `Client`, `Contract`, and `Claim` classes, along with their attributes and methods.

## Development Notes

- The application adheres to Java naming conventions (CamelCase).
- Code is well-commented for clarity.
- Git commits are regular and descriptive, reflecting the development progress.
- The Service layer contains all business logic, with Controllers calling Service methods and Views handling output.

## Troubleshooting

- **Database Connection Issues**: Ensure the JDBC driver is included and the database connection details are correct.
- **Missing Data**: Verify that the database schema is correctly set up using the provided SQL scripts.
- **Java Version Errors**: Confirm that JDK 8 is used, as the application relies on Java 8 features