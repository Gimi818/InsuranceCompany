
# Insurance Company
Application for creating individual Third-Party Liability (OC) and Comprehensive (AC) insurance policies for customers.

During the initial step, customers are required to register with the application to obtain an authentication token. This token grants customers access to the application's functionalities.

In the subsequent step, the client is prompted to input their personal information along with details about the vehicle. The application utilizes the provided data to calculate the vehicle insurance premium.

The application offers a choice between two insurance types:  OC or  OC and AC. The algorithms used to calculate the insurance price incorporate data such as the client's age, engine capacity, vehicle type, garage type, vehicle price, year of production, and average kilometers driven per year.

The client is eligible for a 5% discount on OC and a 2% discount on AC when insuring an additional vehicle. The maximum discount for OC insurance is 40%, and for AC insurance, it is 25%.

In the subsequent step, the application generates an individual policy for the client. The policy includes a unique name, policy type, start and end dates, and the corresponding insurance price.

I used a My SQL database to implement the relationships in the database. The application is deployed on Docker and has an implemented swagger.


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
    {
    "username":"User",
    "password":"Password"
    }
    
  <img src="https://github.com/Gimi818/CarInsurance/blob/master/steps/2N.PNG" width="500" heigt="700"/>
  
    Step 3.1 : 
    Select the authorization field and select the Bearer token option 
    and paste the token you received into the all next steps.
    
  <img src="https://github.com/Gimi818/CarInsurance/blob/master/steps/3N.PNG" width="500" heigt="700"/>
  
    Step 3.2 :
    POST localhost:8080/clients
    Enter personal data. 
    JSON:
    { 
     "firstname": "John",
     "lastname": "Williams",
     "age": 38}
    }
    
  <img src="https://github.com/Gimi818/InsuranceCompany/blob/master/steps/CreateClient.PNG" width="500" heigt="700"/>
  
    Step 4 : 
    POST localhost:8080/cars/1
    Enter the Client ID into the URL and enter vehicle details.
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
      
  <img src="https://github.com/Gimi818/InsuranceCompany/blob/master/steps/AddCar.PNG" width="500" heigt="700"/>
  

    Step 5 : 
    Enter the Client ID into the URL 
    Choose policy: OC or OC and AC and create an individual policy.
    POST localhost:8080/policies/OC/1
    or
    POST localhost:8080/policies/OC/AC/1
   
    
  
   <img src="https://github.com/Gimi818/InsuranceCompany/blob/master/steps/OC.PNG" width="500" heigt="700"/>
   <img src="https://github.com/Gimi818/InsuranceCompany/blob/master/steps/OCAC.PNG" width="500" heigt="700"/>
  
 
    Step 6 : 
    GET localhost:8080/clients/1
    Get the result.
  
  <img src="https://github.com/Gimi818/InsuranceCompany/blob/master/steps/Insurance.PNG" width="500" heigt="700"/>
  
    Endpoints available in the application :
  
   <img src="https://github.com/Gimi818/InsuranceCompany/blob/master/steps/swagger.PNG" width="500" heigt="500"/>

 
