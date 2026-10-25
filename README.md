# Employee Management System

A full-stack Employee Management System developed using Spring Boot, Spring Data JPA, MySQL, Thymeleaf, and Bootstrap as part of Lab Sheet 05.

## Technologies Used

- **Backend:** Java 17, Spring Boot, Spring MVC
- **Database:** MySQL
- **ORM:** Spring Data JPA, Hibernate
- **Frontend:** HTML, Thymeleaf, Bootstrap
- **Validation:** Jakarta Bean Validation
- **Development Tools:** VS Code, Maven, XAMPP, Postman
- **Version Control:** Git and GitHub

## Features

- Register new employees
- Display registered employees
- Update employee information
- Delete employee records
- Validate employee information
- Prevent duplicate employee email addresses
- Store employee records in MySQL
- Responsive web interface

## Project Architecture

The application follows a layered architecture:

1. **Model:** Defines the Employee entity and validation rules.
2. **Repository:** Handles database operations using Spring Data JPA.
3. **Service:** Implements business logic and CRUD operations.
4. **Controller:** Processes HTTP requests and connects the service layer to the user interface.
5. **Templates:** Provides Thymeleaf-based employee management pages.

## Database Configuration

Create a MySQL database named `employee_db`.

Configure `src/main/resources/application.properties` with your local database connection details.

The application uses Hibernate to create or update the required database tables automatically.

## Running the Application

**Prerequisites:** Java 17, MySQL, and Maven Wrapper.

1. Clone the repository.
2. Start the MySQL server.
3. Create the `employee_db` database.
4. Configure the database username and password.
5. Open a terminal in the project directory.
6. Run:

```powershell
.\mvnw.cmd spring-boot:run
```

7. Open `http://localhost:8081/employees` in your browser.

## Application Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | `/employees` | Display employee list and registration form |
| POST | `/employees` | Register a new employee |
| GET | `/employees/{id}/edit` | Display employee edit form |
| POST | `/employees/{id}/update` | Update employee details |
| POST | `/employees/{id}/delete` | Delete an employee |

**Note:** These are Spring MVC endpoints that use HTML forms. POST requests should use form-encoded data rather than JSON.

## Testing

The application was tested using the browser, Postman, and phpMyAdmin.

Browser-based CRUD operations were verified successfully. Postman GET, UPDATE, and DELETE requests were also tested. The Postman CREATE request requires final verification.

## Version Control

The project was developed using a Git feature-branch workflow.

- Main branch: `main`
- Feature branch: `feature/employee-crud`
- Feature commit: `8abc74d`

## Repository

https://github.com/romenmalindafernando/employee-manager-springboot
