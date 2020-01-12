# DB ALE™

DB ALE™ is a java application for diabetic patients allowing them to record their blood glucose, carbs and insulin values, and to display them in an intuitive and visual way. This virtual logbook can also contact patients’ doctor when recorded values are beyond recommended.

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 

Prerequisites
•	Java Development Kit 8 (with JavaFX)
•	IntelliJ IDE

## Running the tests 

When the project is built using gradle, the unit tests are automatically run. If the tests pass, then the build has been successful.
Basic unit testing was done to test the different isolated parts of the system. For instance, the testIsValidEmailAddressFalse tests to see if the function isValidEmailAddress returns false for an invalid email address. If it does, the test has passed.

## Break down into end to end tests

Bugs related to user input need to be fixed.

## Built With

* [JavaFX](https://openjfx.io/) - Front-end UI
* [Gradle](https://gradle.org/) - Building and manage the dependencies
* [PostgreSQL](https://www.postgresql.org/) - Used to generate RSS Feeds
* [Heroku](https://dashboard.heroku.com/) - Database service
* [Travis](https://travis-ci.org/) - Continuous integration and testing
* [JUnit4](https://junit.org/junit4/) - Unit testing

## Versioning

We use GitHub for versioning. For the versions available, see the tags and commits on this repository.

## Authors
•	Noa Appelbom – front-end 
•	Krithika Balaji – alert system + unit testing
•	María de la paz Cardona Sánchez – front-end
•	Marcella Iswanto – back-end
•	Sarah Kratz-Wang – GitHub + deployment

## License
This project is licensed under the MIT License - see the LICENSE.md file for details

## Acknowledgment
* Mr Martin Holloway
* Mr Filip Paszkiewicz
