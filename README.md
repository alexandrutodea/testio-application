
# Testio: Plagiarism-Oriented Educational Programming Platform

Testio is a platform aimed at helping teachers create programming exercises and generate automated plagiarism reports for the solutions students submit.

We use [Spring](https://spring.io/) to deliver Testio as a web application and for the various other features that it offers. Testio's plagiarism component is AST-based. We use [JavaParser](https://javaparser.org/), the most popular parser for the Java language, to process abstract syntax trees for detecting plagiarism in student submissions.

Our AST-based plagiarism detector can identify more instances of plagiarism than token-based and string-based detectors.








## Screenshots

![Create Exercise Page](https://i.imgur.com/6rxNzTh.png)

![Submission List Page](https://i.imgur.com/Xl4jP8t.png)



## Features

- Programming exercise creation
- Instant exercise solution feedback
- Plagiarism detector for student solution submissions
- OAuth 2.0 authorization


## Requirements

- Java 17 or later
- Maven 3.2 or later
- A relational DBMS such as MySQL, PostgreSQL, or MariaDB
- Auth0 regular web application. Refer to [the official Auth0 documentation](https://auth0.com/docs/get-started/auth0-overview/create-applications).



## How to use
### Create exercises
- Press the Create Exercise button on the main screen
- Select the programming language, fill in the exercise description, exercise solution, exercise test class, and the pre-filled code
- Press the Create button
- The platform will save the exercise and make it available for users to solve
### Solve exercises
- Press the Browse Exercises button on the main screen
- Fill in the solution to the displayed exercise
- Press the Submit button
- The platform will display the number of passed unit tests
### View plagiarism reports
- Press the Manage Exercises button
- Select the View Submissions action on a specific exercise
- The platform will display an originality percentage for each student submission to the selected exercise




## Running the project

Run my project with Maven

```bash
  mvn clean package
  java -jar target/testio-0.0.1-SNAPSHOT.jar
```

## Credits

- The algorithm powering Testio is based on [Clone detection using abstract syntax trees, Baxter et al. (1998)](https://ieeexplore.ieee.org/document/738528)
- [JavaParser](https://javaparser.org/) is used for processing the student submission abstract syntax trees
- [Auth0](https://auth0.com/) is used for user authorization
## License

[MIT](https://choosealicense.com/licenses/mit/)

