# Store and Solve Algebraic Equation

## 📌 Project Overview
This project is a **Spring Boot** application that allows users to **store and evaluate algebraic equations** using a **Postfix Expression Tree**. The application provides a REST API to accept algebraic expressions, convert them to postfix notation, construct an expression tree, and compute the result.

## 🚀 Features
- Convert **infix expressions** (e.g., `3 + 5 * 2`) to **postfix notation**.
- Build an **Expression Tree** for efficient evaluation.
- Evaluate **mathematical expressions** and return results.
- REST API endpoints for equation storage and evaluation.

## 🛠️ Tech Stack
- **Java 17**
- **Spring Boot 3** (Spring Web, Spring Data JPA)
- **H2 Database** (or MySQL/PostgreSQL support)
- **Maven**
- **JUnit 5** (for unit testing)

## 📂 Project Structure
```
Store-and-Solve-Algebraic-Equation/
│── src/
│   ├── main/
│   │   ├── java/com/example/algebra/
│   │   │   ├── controllers/    # REST APIs
│   │   │   ├── services/       # Business logic
│   │   │   ├── repositories/   # Database access layer
│   │   │   ├── models/         # Entity models
│   │   │   ├── utils/          # Helper functions (ExpressionTree)
│   │   │   ├── AlgebraApplication.java  # Main app entry point
│   │   ├── resources/
│   │   │   ├── application.properties  # Configurations
│   ├── test/  # Unit tests
│── pom.xml  # Dependencies
│── README.md  # Documentation
```

## 🔧 Installation & Setup
### **1️⃣ Clone the Repository**
```sh
git clone https://github.com/akash-kasaudhan/Store-and-Solve-Algebraic-Equation.git
cd Store-and-Solve-Algebraic-Equation
```

### **2️⃣ Build and Run the Application**
Using **Maven**:
```sh
mvn spring-boot:run
```

Or build a JAR and run it:
```sh
mvn clean package
java -jar target/algebra-app-0.0.1-SNAPSHOT.jar
```

### **3️⃣ Check if the Application is Running**
Visit `http://localhost:8080/actuator/health` in your browser.
Expected response:
```json
{
  "status": "UP"
}
```

## 📡 API Endpoints
| Method | Endpoint | Description |
|--------|-------------|--------------|
| `POST` | `/expression/evaluate` | Accepts an infix equation, converts to postfix, and evaluates it. |

### **Example API Call**
#### **Request:**
```sh
curl -X POST http://localhost:8080/expression/evaluate -H "Content-Type: text/plain" -d "3+5*2"
```
#### **Response:**
```json
{
    "postfix": "352*+",
    "result": 13
}
```

## 🧪 Running Tests
To execute unit tests:
```sh
mvn test
```

## 🚀 Deployment
For **Docker Deployment**, create a `Dockerfile`:
```dockerfile
FROM openjdk:17
COPY target/algebra-app-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
```

Build and run the Docker container:
```sh
docker build -t algebra-app .
docker run -p 8080:8080 algebra-app
```

## 📜 License
This project is **open-source** under the **MIT License**.

## 🤝 Contributing
1. Fork the repository
2. Create a new branch (`feature-xyz`)
3. Commit your changes
4. Push to the branch and create a PR

## 📞 Contact
For any queries, reach out to [Akash Kasaudhan](https://github.com/akash-kasaudhan).

