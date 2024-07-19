# B2B Activity Management (In Development..)

This project provides a platform for managing B2B activities with a focus on administrative and managerial functionalities using Spring Boot, MySQL, and JWT token-based authentication.

## Overview

The B2B Activity Management system allows administrators to create, update, and delete activities, as well as assign and remove managers for those activities. Managers can perform operations on activities assigned to them, enhancing collaboration and productivity in B2B environments.

## Technologies Used

### Backend:

- Spring Boot
- Spring Security
- MySQL database

## Project Structure

The project is structured into several modules/components to ensure clarity and maintainability:

- **AdminController**: Handles administrative tasks such as activity management and manager assignment/removal.
- **ManagerController**: Manages operations related to activities assigned to managers.
- **AuthController**: Provides authentication endpoints for user login and signup.
- **TicketController**: Manages ticket purchases and transactions.
- **ActivityController**: Provides endpoints for managing B2B activities.

## API Endpoints

### Authentication

- **POST** `/api/auth/signup`
  - Description: Creates a new user account.
  - Response: Returns the created user details.

- **POST** `/api/auth/login`
  - Description: Authenticates user credentials and returns a JWT token.
  - Response: Returns JWT token for authenticated user.

### Admin Functions

- **POST** `/admin/activity`
  - Description: Creates a new activity.
  - Response: Returns the created activity details.

- **PUT** `/admin/activity/{activityId}`
  - Description: Updates an existing activity.
  - Response: Returns the updated activity details.

- **DELETE** `/admin/activity/{activityId}`
  - Description: Deletes an activity by its ID.
  - Response: No content on successful deletion.

- **POST** `/admin/activity/{activityId}/manager`
  - Description: Assigns a manager to an activity.
  - Response: Returns the updated activity with assigned manager details.

- **DELETE** `/admin/activity/{activityId}/manager/{managerId}`
  - Description: Removes a manager from an activity.
  - Response: No content on successful removal.

### Manager Functions

- **PUT** `/manager/activity/{activityId}`
  - Description: Updates an activity assigned to the manager.
  - Response: Returns the updated activity details.

### General Functions

- **GET** `/activity`
  - Description: Retrieves all activities.
  - Response: Returns a list of activities.

- **GET** `/activity/{activityId}`
  - Description: Retrieves details of a specific activity by its ID.
  - Response: Returns the activity details.

- **GET** `/activity/filter`
  - Description: Filters activities by category.
  - Request Parameter: `category`
  - Response: Returns a list of activities filtered by the specified category.

- **GET** `/activity/search`
  - Description: Searches activities by keyword.
  - Request Parameter: `keyword`
  - Response: Returns a list of activities matching the search keyword.

## Setup

### Prerequisites

Ensure you have the following installed:

- Java Development Kit (JDK) 17
- Maven
- MySQL Server

### Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/eerenyilmazz/B2B-Activity-Management.git
   cd B2B-Activity-Management
   ```

2. **Backend Setup:**

   - Navigate to `src/main/resources` and configure the database connection in `application.properties`.

   - Run the Spring Boot application:

     ```bash
     mvn spring-boot:run
     ```

3. **Usage**

   - Access the APIs using tools like Postman or integrate them into your frontend application.


## Contact

For any questions or feedback regarding this project, please reach out to me through [GitHub](https://github.com/eerenyilmazz).
