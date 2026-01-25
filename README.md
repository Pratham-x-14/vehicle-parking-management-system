# Vehicle Parking Management System - Setup Guide

This guide explains how to set up and run the Vehicle Parking System on a new laptop or fresh environment.

## 📋 Prerequisites

Before you begin, ensure you have the following installed on your machine:

1.  **Java Development Kit (JDK) 17 or higher**
    *   Verify by running: `java -version`
2.  **Apache Maven**
    *   Verify by running: `mvn -version`
3.  **MySQL Server**
    *   Verify that the service is running.

## 🛠️ Step-by-Step Installation

### 1. Database Setup
You need a MySQL database running.
1.  Open your MySQL Workbench or Command Line.
2.  Create a new database named `parking_system`:
    ```sql
    CREATE DATABASE parking_system;
    ```
    *(Note: The application is configured to try creating this automatically, but creating it manually is safer).*

### 2. Application Configuration
If your MySQL root password is **NOT** `root`, you must update the configuration.

1.  Navigate to the project folder:
    `vehicleParkingSystem/src/main/resources/`
2.  Open `application.properties`.
3.  Update the username and password lines:
    ```properties
    spring.datasource.username=YOUR_MYSQL_USERNAME
    spring.datasource.password=YOUR_MYSQL_PASSWORD
    ```
    *Example:*
    ```properties
    spring.datasource.username=root
    spring.datasource.password=mySecretPassword123
    ```

### 3. Build the Project
Open a terminal (Command Prompt or PowerShell) in the root directory `vehicleParkingSystem` (where `pom.xml` is located) and run:

```bash
mvn clean install
```
*This command downloads all dependencies and builds the project.*

### 4. Run the Application
Start the Spring Boot application using this command:

```bash
mvn spring-boot:run
```

**Successful Start**: You should see logs indicating the application has started on port `8080`.
```
Tomcat started on port(s): 8080 (http) 
Started ParkingSystemApplication in ...
```

---

## 🧪 Testing the Application

Once the application is running, you can test it using **Postman** or **cURL**.

### A. Check Available Slots
**Request:**
- **URL**: `http://localhost:8080/api/parking/available-slots`
- **Method**: `GET`

### B. Park a Vehicle
**Request:**
- **URL**: `http://localhost:8080/api/parking/park`
- **Method**: `POST`
- **Body (JSON)**:
  ```json
  {
      "vehicleNumber": "MH-12-AB-9999",
      "vehicleType": "CAR"
  }
  ```

### C. Unpark a Vehicle
**Request:**
- **URL**: `http://localhost:8080/api/parking/unpark`
- **Method**: `POST`
- **Body (JSON)**:
  ```json
  {
      "vehicleNumber": "MH-12-AB-9999"
  }
  ```

---

## ❓ Troubleshooting

**Issue: `Port 8080 was already in use`**
*   **Solution**: Values in `application.properties` can be changed to use a different port:
    ```properties
    server.port=8081
    ```

**Issue: `Access denied for user 'root'@'localhost'`**
*   **Solution**: Double-check your MySQL password in `application.properties`.

**Issue: `Java not found`**
*   **Solution**: Ensure you have installed Java 17 and added it to your system's Environment Variables (PATH).
