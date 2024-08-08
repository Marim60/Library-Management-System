# Library Management System

## Overview
This project is a Library Management System implemented using Spring Boot. It includes functionalities to manage books and handle basic CRUD operations with API endpoints secured using basic authentication.

## Prerequisites
- Java JDK 17 or later
- Maven for dependency management
- Postman for testing API endpoints (optional)
- H2 Database or your preferred database setup

## Running the Application

### 1. Clone the Repository

To clone the repository, use the following commands:
```bash
git clone https://github.com/Marim60/Library-Management-System.git
cd Library-Management-System

### 2. Build and Run

To build and run the application, use Maven with the following commands:

1. **Build the project:**

    ```bash
    mvn clean install
    ```

2. **Run the application:**

    ```bash
    mvn spring-boot:run
    ```

## API Endpoints

### Authentication

The application uses **Basic Authentication**. Use the following credentials to authenticate:

- **Username:** `admin`
- **Password:** `adminpass`

### Endpoints

For detailed API documentation, refer to the [Postman collection](https://note-mood.postman.co/workspace/Global~129e7d65-cfa6-4456-864e-350351321515/collection/34400135-0eb034de-4685-4d09-a556-1a67a11f501f?action=share&creator=34400135). This collection includes comprehensive details for each endpoint, such as:

- **URL**
- **Method** (GET, POST, PUT, DELETE)
- **Request Body** (if applicable)
- **Response**

You can access the Postman collection here:

[Postman Collection with Documentation](https://note-mood.postman.co/workspace/Global~129e7d65-cfa6-4456-864e-350351321515/collection/34400135-0eb034de-4685-4d09-a556-1a67a11f501f?action=share&creator=34400135)
