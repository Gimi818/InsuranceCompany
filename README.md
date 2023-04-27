# Car Insurance
## Web application for calculating car insurance prices and creating policies.

The application can be used by an insurance company to calculate insurance for customers and store their customer data.

In the first step, the customer must register with the application in order to receive an authentication token,
which will give the customer the ability to use the functions of the application.
The next step is for the customer to enter his personal data and information about 
the car he wants to insure, which are saved in a database.
The application, using the data provided by the 
client and calculates the price of his car insurance.
The logic for calculating the insurance price uses data such as :
client's age, engine capacity, vehicle type, vehicle price, year of production, average kilometres driven per year.
In the next step, the application creates an individual policy assigned to the client and the car in question.
The policy contains a unique name, policy start and end date and insurance price. 
I used a Mysql database to implement the relationships in the database
 One customer can have multiple vehicles and one vehicle can have one insurance policy
 
 ## Application is developed using following technologies:
 Core:
<p align="left"><a href="https://www.java.com" target="_blank" rel="noreferrer"> 
<img src="https://ultimateqa.com/wp-content/uploads/2020/12/Java-logo-icon-1.png" alt="java" width="80" height="50"/> 
</a> <a href="https://spring.io/" target="_blank" rel="noreferrer"> <img src="https://e4developer.com/wp-content/uploads/2018/01/spring-boot.png" alt="spring" width="90" height="50"/> 
<a href="https://www.mongodb.com/" target="_blank" rel="noreferrer"> <a href="https://www.docker.com/" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/docker/docker-original-wordmark.svg" alt="docker" width="50" height="50"/>
 <a href="https://git-scm.com/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/git-scm/git-scm-icon.svg" alt="git" width="50" height="50"/> </a> 
 <a href="https://www.mysql.com/" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/mysql/mysql-original-wordmark.svg" alt="mysql" width="40" height="40"/> </a>
 <a href="https://www.docker.com/" target="_blank" rel="noreferrer"> <img src="https://mapstruct.org/images/mapstruct.png" alt="docker" width="80" height="50"/></a>
 </a> <a href="https://www.java.com" target="_blank" rel="noreferrer"> <img src="https://junit.org/junit4/images/junit5-banner.png" alt="java" width="90" height="50"/>
 <a href="https://www.java.com" target="_blank" rel="noreferrer"> <img src="https://javadoc.io/static/org.mockito/mockito-core/1.9.5/org/mockito/logo.jpg" alt="java" width="90" height="50"/></a> </p>
