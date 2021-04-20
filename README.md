# Api Testing Assignment

## How to install and run
- Clone the repository from GitHub
- Install Docker
- cd into the project directory
- First, create a 'target' directory for reports to be placed in after the tests are done in docker file

```
mkdir target
``` 
- Build the docker image from the Dockerfile, you should be in the same directory with the Dockerfile for this command to work as expected:
```
docker build -t bynder-api-test-img .
```   

- After build is completed, run the image by binding the target directory to the image:
```
docker run -it -v ${PWD}/target:/app/target bynder-api-test-img
``` 

After the tests are done, the container will exit and hand over the CLI back. <br>
Then check the **'target/site/surefire-reports.html'** directory for the  surefire html report.
<br>



You can see the endpoints interactions below;

<br>


**Test Scenarios**

**Scenario:**  Create board with  POST /1/boards/ endpoint with Valid Api_key and param<br>
&nbsp; **Given** I accept ContentType.JSON<br>
&nbsp; **When** I call create board POST/1/boards/ endpoint with valid api_key param<br>
&nbsp; **Then** the statusCode should be 200<br>
&nbsp; **And** the response ContentType should be Json<br>
&nbsp; **And** the board should be created<br>

**Scenario:**  Create list with  POST /1/lists endpoint with Valid Api_key and param<br>
&nbsp; **Given** I accept ContentType.JSON<br>
&nbsp; **When** I call create board POST /1/lists endpoint with valid api_key param<br>
&nbsp; **Then** the statusCode should be 200<br>
&nbsp; **And** the response ContentType should be Json<br>
&nbsp; **And** the card should be created<br>

**Scenario:**  Create 3 card with  POST /1/cards endpoint with Valid Api_key and param<br>
&nbsp; **Given** I accept ContentType.JSON<br>
&nbsp; **When** I call create card POST /1/cards endpoint 3 times with valid api_key param<br>
&nbsp; **Then** the statusCode should be 200<br>
&nbsp; **And** the response ContentType should be Json<br>
&nbsp; **And** the card should be created<br>

**Scenario:**  Edit one of the card with  PUT /1/cards/ endpoint with Valid Api_key and param<br>
&nbsp; **Given** I accept ContentType.JSON<br>
&nbsp; **When** I call edit card PUT /1/cards/ with valid api_key param<br>
&nbsp; **Then** the statusCode should be 200<br>
&nbsp; **And** the response ContentType should be Json<br>
&nbsp; **And** the card should be edited<br>

**Scenario:**  Delete one of the card with  DELETE /1/cards/ endpoint with Valid Api_key and param<br>
&nbsp; **Given** I accept ContentType.JSON<br>
&nbsp; **When** I call delete card DELETE /1/cards/ with valid api_key param<br>
&nbsp; **Then** the statusCode should be 200<br>
&nbsp; **And** the response ContentType should be Json<br>
&nbsp; **And** the card should be deleted<br>

**Scenario:**  Add comment with  POST /1/cards/{id}/actions/comments endpoint with Valid Api_key and param<br>
&nbsp; **Given** I accept ContentType.JSON<br>
&nbsp; **When** I call delete card POST /1/cards/{id}/actions/comments with valid api_key param<br>
&nbsp; **Then** the statusCode should be 200<br>
&nbsp; **And** the response ContentType should be Json<br>
&nbsp; **And** the comment should be added<br>

Credentials:
email:megastokk@gmail.com
password: secretpasword