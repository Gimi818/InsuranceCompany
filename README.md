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
 
 ## How to use apllication in postman
 
    Step 1 :
    POST localhost:8080/register 
    Enter your username and password. 
  
  <img src="https://github.com/Gimi818/CarInsurance/blob/master/steps/1.PNG" width="500" heigt="700"/>
  
    Step 2 :
    POST localhost:8080/token
    Enter username and password to get token.
    
  <img src="https://github.com/Gimi818/CarInsurance/blob/master/steps/2.PNG" width="500" heigt="700"/>
  
    Step 3.1 : 
    Select the authorization field and select the Bearer token option 
    and paste the token you received
    
  <img src="https://github.com/Gimi818/CarInsurance/blob/master/steps/3.1.PNG" width="500" heigt="700"/>
  
    Step 3.2 :
    POST localhost:8080/clients/add
    Enter personal data 
    And add your token to the authorisation
  
  <img src="https://github.com/Gimi818/CarInsurance/blob/master/steps/3.2.PNG" width="500" heigt="700"/>
  
    Step 4 : 
    POST localhost:8080/cars/add
    Enter vehicle details
    And add your token to the authorisation
  
  <img src="https://github.com/Gimi818/CarInsurance/blob/master/steps/4.PNG" width="500" heigt="700"/>
  
    Step 5 : 
    PUT localhost:8080/clients/1/cars/1
    Assign the car to the client by id
    And add your token to the authorisation
  
  <img src="https://github.com/Gimi818/CarInsurance/blob/master/steps/5.PNG" width="500" heigt="700"/>
  
    Step 6 : 
    POST localhost:8080/policies/1/cars/1
    Calculate insurance prices and create policy
    And add your token to the authorisation
  
  <img src="https://github.com/Gimi818/CarInsurance/blob/master/steps/6.PNG" width="500" heigt="700"/>
  
    Step 7 : 
    PUT localhost:8080/cars/1/policies/1
    Assing the policy to the car by id
    And add your token to the authorisation
  
  <img src="https://github.com/Gimi818/CarInsurance/blob/master/steps/7.PNG" width="500" heigt="700"/>
  
    Step 8 : 
    GET localhost:8080/clients/1
    Get all your data
    And add your token to the authorisation
  
  <img src="https://github.com/Gimi818/CarInsurance/blob/master/steps/8.PNG" width="500" heigt="700"/>
  
    Endpoints available in the application :
  
   <img src="https://github.com/Gimi818/CarInsurance/blob/master/steps/endPoints.PNG" width="500" heigt="500"/>
 

