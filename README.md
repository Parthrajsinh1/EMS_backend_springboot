# EMS Backend — Employee Management System (Spring Boot)

A RESTful backend for an Employee Management System built using **Spring Boot**, **Spring Data JPA**, and **Maven**.  
It supports CRUD operations on employees with proper exception handling and a clean layered architecture.



## Overview
This project provides backend services for managing employees.  
Features include:
- Create, Read, Update, Delete (CRUD) employees
- Service layer for business logic
- Repository layer for database operations
- Centralized exception handling
- Standard API response wrapper

---

##  Tech stack
- **Java** (17) 
- **Spring Boot** (Web, Data JPA)  
- **Hibernate / JPA**  
- **Maven** (wrapper included)  
- **MySQL / H2 Database**  
- **JUnit** (for testing)

---

##  Project structure

```
src/
  main/
    java/
      com/app/                 
        Day19EmsBackendApplication.java   # Spring Boot entry point
        controller/
          EmployeeController.java         # REST controller
        custome_exceptions/
          ResourceNotFoundException.java  # Custom exception
        dao/
          EmployeeRepository.java         # JPA repository
        dto/
          ApiResponse.java                # DTO for responses
        entities/
          BaseEntity.java                 
          Employee.java                   # Employee entity
        exc_handler/
          GlobalExceptionHandler.java     # Centralized exception handling
        service/
          IEmployeeService.java           # Service interface
          EmployeeServiceImpl.java        # Service implementation
    resources/
      application.properties              # Spring configuration
  test/
    java/com/app/
      Day19EmsBackendApplicationTests.java  # Unit tests
pom.xml                                   # Maven build file
mvnw, mvnw.cmd                            # Maven wrapper scripts
.mvn/wrapper/                             # Maven wrapper config

```

 API Endpoints
----------------

**Base URL:** `http://localhost:9090`

### Employees

| Method | Endpoint           | Description              |
|--------|------------------|--------------------------|
| GET    | `/employees`      | Get all employees        |
| GET    | `/employees/{id}` | Get an employee by ID    |
| POST   | `/employees`      | Create a new employee    |
| PUT    | `/employees/{id}` | Update an existing employee |
| DELETE | `/employees/{id}` | Delete an employee by ID |

Error Handling
----------------

- When a requested employee does not exist, the application throws a `ResourceNotFoundException`.
- The `GlobalExceptionHandler` captures exceptions and returns consistent, structured JSON responses.

### Example Error Response

```json
{
  "timestamp": "2025-09-25T18:30:00",
  "message": "Employee not found with id 5",
  "details": "/employees/5"
}
