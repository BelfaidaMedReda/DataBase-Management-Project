# Database Design Project

## Project Description

This academic project was carried out as part of the Database course at Grenoble INP - Ensimag. The goal was to design and implement a database for an auction management system. The work involved problem modeling, translating it into a relational schema, and developing advanced functionalities using SQL queries and a Java program.


## Implemented Features

### 1. Auction Room Creation
- Enables the creation of an auction room with specific characteristics (category, auction type, etc.).
- Displays the auctions associated with the newly created or existing room.

### 2. Offer Management
- Verifies and validates offers submitted by users based on defined criteria (available quantity, auction type, etc.).
- Adds valid offers to the database.

### 3. Identification of Completed Auctions and Winner Determination
- Lists completed auctions based on their type (limited or unlimited).
- Determines winners by allocating available lots to the most advantageous offers.

## Methodology

### 1. Problem Analysis
- Identification of value constraints, functional dependencies, and multiplicity constraints.
- Analysis of contextual constraints specific to auctions.

### 2. Entity-Relationship Design
- UML modeling of the problem.
- Management of complex cases such as auctions with limited offers.

### 3. Relational Schema Translation
- Creation of a relational schema from the Entity-Relationship model.
- Normalization up to 3NF to ensure an optimal design.

### 4. SQL Functionality Implementation
- Use of SQL queries to create, manage, and interact with the database.
- Integration with Java to develop an interactive demonstrator.

## Outcome

The project allowed us to:
- Work effectively as a team despite organizational challenges.
- Develop a robust relational schema based on thorough analysis.
- Design a functional demonstrator showcasing the main features of the system.

## Instructions to Run the Demonstrator

1. Navigate to the `src/Java` directory.
2. Compile the program with:
   ```bash
   javac Demonstrateur.java
   ```
3. Run the demonstrator with:
   ```bash
   java Demonstrateur
   ```
4. Follow the instructions displayed in the interactive menu.

**Note:** When creating a room, ensure you enter the exact name of the desired category in the terminal.

## Timeline
- **Week 1**: Entity-Relationship schema creation.
- **Week 2**: Relational schema creation.
- **Week 3**: Implementation on the server and development of functionalities.

---

This project represents an excellent opportunity to apply database concepts to a realistic problem, while strengthening skills in modeling, SQL, and collaborative development.
