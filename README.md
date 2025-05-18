# Human Resources Management System (HRMS) - IUG

## Overview
The Human Resources Management System (HRMS) is a comprehensive solution developed for the University of Gaza (IUG) to streamline and automate various HR processes. This system is designed to enhance the efficiency of managing university staff, faculty members, and administrative personnel.

## Features
- Employee Management
  - Staff profiles and information management
  - Employment history tracking
  - Document management
  - Leave management
- Department Management
  - Department structure
  - Staff allocation
  - Department budgets
- Payroll Management
  - Salary processing
  - Benefits administration
  - Tax calculations
- Recruitment and Onboarding
  - Job posting management
  - Application tracking
  - New employee onboarding
- Performance Management
  - Performance reviews
  - Goal setting
  - Career development tracking
- Reporting and Analytics
  - HR metrics
  - Custom reports
  - Data visualization

## Technical Stack
- Java 17
- MySQL
- Maven
- JUnit for testing

## Getting Started

### Prerequisites
- Java JDK 17 or higher
- MySQL 8.0 or higher
- Maven 3.6.x or higher

### Installation
1. Clone the repository
```bash
git clone https://github.com/your-ramyhelow/Human-Resources-Management-System.git
```

2. Set up the database
```bash
# Create a MySQL database named 'iug_hrms'
# Update the database configuration in application.properties
```

3. Build and run the application
```bash
mvn clean install
mvn exec:java
```

## Project Structure
```
iug-hrms/
├── src/
│   ├── main/
│   │   ├── java/
│   │   └── resources/
│   └── test/
├── pom.xml
└── README.md
```

## Contributing
1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request