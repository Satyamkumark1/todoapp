# Todo Application

A simple and efficient Todo application built with Spring Boot, featuring a clean web interface for managing tasks.

## 🚀 Features

- **Create Tasks**: Add new tasks with titles
- **View Tasks**: Display all tasks in a clean list format
- **Toggle Completion**: Mark tasks as complete or incomplete
- **Delete Tasks**: Remove tasks from the list
- **Persistent Storage**: Tasks are stored in MySQL database
- **Responsive UI**: Clean and intuitive web interface using Thymeleaf

## 🛠️ Technology Stack

- **Backend**: Spring Boot 3.5.4
- **Database**: MySQL
- **ORM**: Spring Data JPA with Hibernate
- **Template Engine**: Thymeleaf
- **Build Tool**: Maven
- **Java Version**: 17
- **Additional**: Lombok for reducing boilerplate code

## 📋 Prerequisites

Before running this application, make sure you have the following installed:

- **Java 17** or higher
- **Maven** 3.6+ 
- **MySQL** 8.0+ server
- **Git** (for cloning the repository)

## 🗄️ Database Setup

1. **Install MySQL** if you haven't already
2. **Create a database**:
   ```sql
   CREATE DATABASE todo-app;
   ```
3. **Update database credentials** in `src/main/resources/application.properties`:
   ```properties
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

## 🚀 Getting Started

### 1. Clone the Repository
```bash
git clone <repository-url>
cd todoapp
```

### 2. Configure Database
Edit `src/main/resources/application.properties` and update the database credentials:
```properties
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3. Build the Application
```bash
./mvnw clean install
```

### 4. Run the Application
```bash
./mvnw spring-boot:run
```

Alternatively, you can run the JAR file:
```bash
java -jar target/todoapp-0.0.1-SNAPSHOT.jar
```

### 5. Access the Application
Open your web browser and navigate to:
```
http://localhost:8080
```

## 📁 Project Structure

```
todoapp/
├── src/
│   ├── main/
│   │   ├── java/com/app/todoapp/
│   │   │   ├── controller/
│   │   │   │   └── TaskController.java      # REST endpoints
│   │   │   ├── models/
│   │   │   │   └── Task.java               # Entity model
│   │   │   ├── repositery/
│   │   │   │   └── TaskRepo.java           # Data access layer
│   │   │   ├── services/
│   │   │   │   └── TaskService.java        # Business logic
│   │   │   └── TodoappApplication.java     # Main application class
│   │   └── resources/
│   │       ├── application.properties      # Configuration
│   │       ├── static/                     # Static resources
│   │       └── templates/
│   │           └── tasks.html              # Thymeleaf template
│   └── test/                               # Test files
├── pom.xml                                 # Maven configuration
└── README.md                               # This file
```

## 🔧 Configuration

The application uses the following key configurations:

- **Database**: MySQL with JPA/Hibernate
- **Server Port**: 8080 (default)
- **Database Schema**: Auto-generated using `hibernate.ddl-auto=update`
- **Template Engine**: Thymeleaf for server-side rendering

## 📖 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/` | Display all tasks |
| POST | `/` | Create a new task |
| GET | `/{id}/toggle` | Toggle task completion status |
| GET | `/{id}/delete` | Delete a task |

## 🧪 Testing

Run the test suite:
```bash
./mvnw test
```

## 🐛 Troubleshooting

### Common Issues

1. **Database Connection Error**:
   - Ensure MySQL is running
   - Verify database credentials in `application.properties`
   - Check if the database `todo-app` exists

2. **Port Already in Use**:
   - Change the server port in `application.properties`:
     ```properties
     server.port=8081
     ```

3. **Build Errors**:
   - Ensure Java 17+ is installed
   - Run `./mvnw clean install` to rebuild

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👨‍💻 Author

- **Your Name** - *Initial work* - [YourGitHub](https://github.com/yourusername)

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- MySQL team for the database
- Thymeleaf team for the template engine 