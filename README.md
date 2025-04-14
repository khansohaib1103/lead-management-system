# Lead Management System - Backend Technical Documentation

## 1. Background

### Purpose
The Lead Management System backend is a Spring Boot application that provides RESTful API endpoints for managing customer leads. It handles data persistence, business logic, and API communication for the frontend application. The backend is responsible for storing lead information, processing requests, and maintaining data integrity.

### Tech Stack
- Spring Boot (v3.x)
- Java JDK 11
- Spring Data JPA
- Spring Web
- MySQL 8.0
- Maven
- Lombok
- Spring Boot DevTools

### Setup Instructions

#### Prerequisites
- Java JDK 11 or higher
- MySQL 8.0 or higher
- Maven 3.6 or higher

#### Installation Steps
1. Clone the repository
2. Navigate to the backend directory
```bash
cd backend
```

3. Configure MySQL database in `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/lead_management
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
```

4. Build the application:
```bash
mvn clean install
```

5. Run the application:
```bash
mvn spring-boot:run
```

## 2. Module Requirements

### Functional Requirements
1. Lead Management
   - Create new leads with contact information
   - Retrieve all leads
   - Delete existing leads
   - Support multiple leads with the same email address
   - Validate lead data before persistence

2. Data Validation
   - Validate required fields (name, email, phone, company)
   - Validate email format
   - Validate phone number format
   - Handle data type conversions

3. API Management
   - Provide RESTful endpoints for CRUD operations
   - Handle CORS configuration
   - Implement proper error handling
   - Support JSON request/response formats

### Non-Functional Requirements
1. Performance
   - API response time under 500ms
   - Support concurrent user access
   - Efficient database queries

2. Security
   - Input validation and sanitization
   - CORS configuration
   - Error handling without exposing sensitive information

3. Reliability
   - Data consistency
   - Transaction management
   - Error recovery mechanisms

4. Maintainability
   - Clean code structure
   - Proper documentation
   - Logging implementation

## 3. Module/Feature Implementation Details

### Folder Structure
```
backend/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── leadmanagement/
│       │           ├── controller/    # REST API controllers
│       │           ├── service/       # Business logic
│       │           ├── repository/    # Data access layer
│       │           ├── entity/        # JPA entities
│       │           ├── dto/           # Data Transfer Objects
│       │           ├── exception/     # Custom exceptions
│       │           └── LeadManagementApplication.java
│       └── resources/
│           └── application.properties # Configuration
└── pom.xml           # Project dependencies
```

### Key Components

#### LeadController.java
```java
// Handles HTTP requests for lead management
// Provides endpoints for CRUD operations
```

#### LeadService.java
```java
// Implements business logic for lead management
// Handles data validation and processing
```

#### LeadRepository.java
```java
// Manages database operations for leads
// Extends JpaRepository for CRUD operations
```

## 4. Database Schema Documentation

### Lead Table Schema
```sql
CREATE TABLE leads (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    company_name VARCHAR(255) NOT NULL,
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### Field Descriptions
- `id`: UUID Primary Key, automatically generated
- `full_name`: Lead's full name (required)
- `email`: Contact email address (required, not unique)
- `phone_number`: Contact phone number (required, supports formatted numbers)
- `company_name`: Company name (required)
- `notes`: Additional notes (optional)
- `created_at`: Timestamp of lead creation (automatically set)

## 5. API Contracts

### Endpoints

#### GET /api/leads
- Purpose: Retrieve all leads
- Response Format:
```json
[
  {
    "id": "5bb1c691-2023-46ab-8c04-2e729a22d9dd",
    "fullName": "Sohaib Khan",
    "email": "Sohaibk1999@gmail.com",
    "phoneNumber": "03175788356",
    "companyName": "FutureNostics",
    "notes": "Hello World!",
    "createdAt": "2025-04-15T01:30:09.309555"
  }
]
```

#### POST /api/leads
- Purpose: Create new lead
- Request Format:
```json
{
  "fullName": "Sohaib Khan",
  "email": "sohaibk1999@gmail.com",
  "phoneNumber": "0317-5788356",
  "companyName": "futurenostics",
  "notes": "Optional notes"
}
```
- Response: Created lead object with generated UUID and timestamp

#### DELETE /api/leads/{id}
- Purpose: Delete a lead
- URL Parameter: UUID of the lead to delete
- Example: DELETE /api/leads/5bb1c691-2023-46ab-8c04-2e729a22d9dd
- Response: 200 OK on success

### Common Response Codes
- 200: Success
- 201: Created (for successful POST requests)
- 400: Bad Request (invalid input data)
- 404: Not Found (lead ID doesn't exist)
- 500: Server Error

### Data Formats
- IDs: UUID v4 format
- Dates: ISO 8601 format (e.g., "2025-04-15T01:30:09.309555")
- Phone Numbers: Accepts formatted strings (e.g., "0317-5788356" or "03175788356")
- Email: Case-insensitive, allows multiple leads with same email
- Text Fields: UTF-8 encoded strings

## 6. Testing/Error Handling

### Error Handling
1. Validation Errors
   - 400 Bad Request for invalid input
   - Detailed error messages for validation failures
   - Proper error response format

2. Server Errors
   - 500 Internal Server Error for unexpected issues
   - Logging of errors for debugging
   - Graceful error handling

### Deployment Guide

#### Local Deployment
1. Build the application:
```bash
mvn clean package
```

2. Run the application:
```bash
java -jar target/lead-management-0.0.1-SNAPSHOT.jar
```

#### Production Deployment Considerations
- Set up environment variables
- Configure production database
- Set up CORS for production domain
- Configure logging
- Set up monitoring
- Configure backup strategy

---

For any questions or issues, please contact the development team. 