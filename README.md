
# Insurance Company
The application can be used by an insurance company to calculate and create OC and OC/AC insurance for customers.

In the first step, the customer must register with the application in order to receive an authentication token, which will give the customer the ability to use the functions of the application. 

The next step is for the customer to enter his personal data and information about the car, which are saved in a database. The application, using the data provided by the client and calculates the price of his car insurance. 

In the application, there is a choice of two types of insurance OC or OC and AC. The logic for calculating the insurance price uses data such as : client's age, engine capacity, vehicle type,  garage type, vehicle price, year of production and average kilometers driven per year.

The client receives a 5% discount on third-party liability insurance (OC) and a 2% discount on comprehensive insurance (AC) for insuring an additional vehicle. The maximum discount for OC insurance is 40% and 25% for AC insurance. 

In the next step, the application creates an individual policy assigned to the client and the car. The policy has a unique name, type of policy, policy start and end date and insurance price. 

I used a My SQL database to implement the relationships in the database one customer can have multiple vehicles and one vehicle can have one insurance policy. The application is deployed on Docker and has an implemented swagger.


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
 
 ## To run the application, follow these steps :
- Install IntelliJ IDEA and Docker Desktop on your computer.
- Run Docker Desktop.
- Clone the repository in IntelliJ IDEA using the link https://github.com/Gimi818/InsuranceCompany
- Run docker-compose up in the terminal. 
- Run the applications in IntelliJ IDEA.
- Try the applications in Postaman, the steps on how to do it are below.
 
 ## How to use application in postman :
 
    Step 1 :
    POST localhost:8080/register 
    Enter your username and password.
    JSON:
    {"username":"User",
    "password":"Password"}
  
  <img src="https://github.com/Gimi818/CarInsurance/blob/master/steps/1N.PNG" width="500" heigt="700"/>
  
    Step 2 :
    POST localhost:8080/token
    Enter your username and password to get the token.
    JSON:
    {"username":"User",
    "password":"Password"}
    
  <img src="https://github.com/Gimi818/CarInsurance/blob/master/steps/2N.PNG" width="500" heigt="700"/>
  
    Step 3.1 : 
    Select the authorization field and select the Bearer token option 
    and paste the token you received into the all next steps.
    
  <img src="https://github.com/Gimi818/CarInsurance/blob/master/steps/3N.PNG" width="500" heigt="700"/>
  
    Step 3.2 :
    POST localhost:8080/clients/add
    Enter personal data. 
    JSON:
    { "firstname": "John",
     "lastname": "Williams",
     "age": 35}
  
  <img src="https://github.com/Gimi818/CarInsurance/blob/master/steps/4N.PNG" width="500" heigt="700"/>
  
    Step 4 : 
    POST localhost:8080/cars/add
    Enter vehicle details.
    Available options for:
    carModel: CAR, LORRY.
    parkingType: GARAGE, DRIVEWAY, ROAD.
    JSON:
     {"brand": "Bmw",
     "model": "X5",
     "carValue": 83500,
     "carModel": "CAR",
     "parkingType": "GARAGE",
     "yearOfManufacture": 2016,
     "enginCapacity": 3.0,
     "averageKmTraveledPerYear": 19500}
      
  <img src="https://github.com/Gimi818/CarInsurance/blob/master/steps/5N.PNG" width="500" heigt="700"/>
  
    Step 5 : 
    PUT localhost:8080/clients/1/cars/1
    Assign the car to the client.
  
  <img src="https://github.com/Gimi818/CarInsurance/blob/master/steps/6N.PNG" width="500" heigt="700"/>
  
  
    Step 6 : 
    POST localhost:8080/policies/OC/AC/1/cars/1
    or
    POST localhost:8080/policies/OC/1/cars/1
   
    Choose policy: OC or OC and AC and create an individual policy.
  
   <img src="https://github.com/Gimi818/CarInsurance/blob/master/steps/7N.PNG" width="500" heigt="700"/>
   <img src="https://github.com/Gimi818/CarInsurance/blob/master/steps/8N.PNG" width="500" heigt="700"/>
  
    Step 7 : 
    PUT localhost:8080/cars/1/policies/1
    Assign the policy to the car.
  
  <img src="https://github.com/Gimi818/CarInsurance/blob/master/steps/9N.PNG" width="500" heigt="700"/>
  
    Step 8 : 
    GET localhost:8080/clients/1
    Get the result.
  
  <img src="https://github.com/Gimi818/CarInsurance/blob/master/steps/10N.PNG" width="500" heigt="700"/>
  
    Endpoints available in the application :
  
   <img src="https://github.com/Gimi818/CarInsurance/blob/master/steps/endpointsN.PNG" width="500" heigt="500"/>
 
 ## Tests in application: 
 <img src="https://github.com/Gimi818/CarInsurance/blob/master/steps/testsCoverage.PNG" width="500" heigt="700"/>
 <img src="https://github.com/Gimi818/CarInsurance/blob/master/steps/TestsN1.PNG" width="500" heigt="700"/>
 <img src="https://github.com/Gimi818/CarInsurance/blob/master/steps/TestsN2.PNG" width="500" heigt="700"/>
 <img src="https://github.com/Gimi818/CarInsurance/blob/master/steps/TestsN3.PNG" width="500" heigt="700"/>
 
 
