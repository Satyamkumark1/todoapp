# Todo App

A comprehensive Spring Boot application for managing tasks with advanced features.

## Features

### Core Task Management
- ✅ **Create Tasks** - Add new tasks with title, description, category, and priority
- ✅ **Edit Tasks** - Modify existing task details including title, description, category, and priority
- ✅ **Delete Tasks** - Remove tasks from the system
- ✅ **Toggle Completion** - Mark tasks as completed or pending
- ✅ **Task Descriptions** - Add detailed descriptions to tasks
- ✅ **Task Categories** - Organize tasks by categories (Work, Personal, Shopping, Health, Education, Other)
- ✅ **Task Priority Levels** - Set priority levels (High, Medium, Low) with visual indicators
- ✅ **Task Search & Filtering** - Search tasks by title and filter by status, category, and priority

### User Interface
- Modern, responsive Bootstrap-based UI
- Visual priority indicators with color-coded borders
- Category and priority badges
- Search and filter interface
- Confirmation dialogs for destructive actions
- Empty state handling
- Task creation timestamps

## Technology Stack

- **Backend**: Spring Boot 3.x, Spring Data JPA, Spring MVC
- **Database**: MySQL
- **Frontend**: Thymeleaf, Bootstrap 5, Bootstrap Icons
- **Build Tool**: Maven

## Getting Started

### Prerequisites
- Java 17 or higher
- MySQL 8.0 or higher
- Maven 3.6 or higher

### Database Setup
1. Create a MySQL database named `todo-app`
2. Update database credentials in `src/main/resources/application.properties`

### Running the Application
1. Clone the repository
2. Navigate to the project directory
3. Run: `./mvnw spring-boot:run`
4. Access the application at: `http://localhost:8080`

## API Endpoints

- `GET /` - Display all tasks
- `POST /` - Create a new task
- `GET /{id}/edit` - Show edit form for a task
- `POST /{id}/edit` - Update an existing task
- `GET /{id}/delete` - Delete a task
- `GET /{id}/toggle` - Toggle task completion status
- `GET /search` - Search and filter tasks

## Task Model

Each task contains:
- **ID**: Unique identifier
- **Title**: Task title (required)
- **Description**: Detailed task description (optional)
- **Category**: Task category (Work, Personal, Shopping, Health, Education, Other)
- **Priority**: Priority level (High, Medium, Low)
- **Completed**: Completion status (boolean)
- **Created At**: Timestamp of task creation

## Search and Filter Options

- **Search by Title**: Case-insensitive search in task titles
- **Filter by Status**: All, Pending, or Completed tasks
- **Filter by Category**: Filter by specific task categories
- **Filter by Priority**: Filter by priority levels
- **Combined Filters**: Use multiple filters simultaneously

## Future Enhancements

Potential features for future development:
- User authentication and authorization
- Due dates and reminders
- Task sharing and collaboration
- File attachments
- Task templates
- Advanced analytics and reporting
- Mobile app support
- API for external integrations 