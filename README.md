# IT-Conference-2022

IT-Conference-2022 is a web-app written in Java for an IT conference service. The project is a Spring Boot based application, which makes use of an H2 in-memory database. Access to the system is possible by REST API. 

## How to use

Enter the project's main directory. Then, run [mvn](https://maven.apache.org/run.html) command to run the project. 

```bash
mvnw spring-boot:run
```
The most convenient way to test the API is to enter **localhost:{hostport}/swagger-ui/**. There you can find all available RestController API methods. It is also possible to access the H2 database console by entering **localhost:{hostport}/h2-console**. The Driver Class is '**org.h2.Driver**', the JDBC URL is '**jdbc:h2:mem:test**', the user name is '**user**', and the password to the console is '**password**'. 

## About the endpoints
Url addresses are divided into three groups: Lectures, Registrations, and Users. Each of them consists of the corresponding endpoints. 

### Lectures
* GET: /lectures/allByUserLogin - takes the user's login as a parameter and returns a list of lectures, the user is registered for
```json
[
  {
    "registrationId": "19b7759e-f798-47b7-9b50-9a66ef09e05f",
    "topic": "First lecture",
    "startTime": "10:00"
  },
  {
    "registrationId": "b616b8a7-7f74-42bd-946f-875efaad263a",
    "topic": "Second lecture",
    "startTime": "12:00"
  }
]
```
* GET: /lectures/conferenceInfo - returns general information on the conference together with the list of lectures
```json
{
  "date": "2020-06-01",
  "timeSlot1": "10:00 - 11:45",
  "timeSlot2": "12:00 - 13:45",
  "timeSlot3": "14:00 - 15:45",
  "lectures": [
    "id='93f7dc28-0f26-4649-9e98-702aa295632b', path='First path', title='First lecture', startTime='10:00', availablePlaces=4",
    "id='bbe18277-4498-4fb6-b2b9-3e80af5d67b7', path='First path', title='Second lecture', startTime='12:00', availablePlaces=5",
    "id='252596f8-7788-4551-850d-1a38e8b139e1', path='First path', title='Third lecture', startTime='14:00', availablePlaces=5",
    "id='2affc68e-822b-47b6-b696-1b9dba5aef52', path='Second path', title='First lecture', startTime='10:00', availablePlaces=5",
    "id='bec1edb2-5bd5-4fdb-b47b-3b62fc4f3db1', path='Second path', title='Second lecture', startTime='12:00', availablePlaces=5",
    "id='c01253f7-705d-4dd3-aa3f-27c677d0976d', path='Second path', title='Third lecture', startTime='14:00', availablePlaces=5",
    "id='bb39594a-de0c-4222-8209-35f54d6f3ad2', path='Third path', title='First lecture', startTime='10:00', availablePlaces=5",
    "id='8c61da0f-4f6d-47cd-8053-eeeccfc32471', path='Third path', title='Second lecture', startTime='12:00', availablePlaces=4",
    "id='712bf3ae-6291-4fb3-83a8-3e4f9f16f805', path='Third path', title='Third lecture', startTime='14:00', availablePlaces=5"
  ]
}
```
* GET: /lectures/lecturesInterest - returns a list of lectures alongside the percentage of registrations for each lecture
```json
[
  {
    "title": "Second lecture",
    "path": "Third path",
    "startTime": "12:00",
    "registrationsPercentage": 40
  },
  {
    "title": "First lecture",
    "path": "First path",
    "startTime": "10:00",
    "registrationsPercentage": 20
  },
  {
    "title": "Third lecture",
    "path": "Third path",
    "startTime": "14:00",
    "registrationsPercentage": 0
  }
]
```
* GET: /lectures/pathsInterest - returns a list of learning paths alongside the percentage of registrations for each path
```json
[
  {
    "pathName": "Third path",
    "registrationsPercentage": 13.333333333333334
  },
  {
    "pathName": "First path",
    "registrationsPercentage": 6.666666666666667
  },
  {
    "pathName": "Second path",
    "registrationsPercentage": 0
  }
]
```
### Registrations
* POST: /registrations/create - creates a registration for a lecture based on the request model passed in the request's body. The model includes the lecture's id, user's login, and user's email. It is possible to sign up for a lecture only if there are still places available. Additionally, if there already exists the user with given login and a different email, he or she will be asked to enter a new login. Also, each registration results in sending a confirmation email, which is a new row in a file '**powiadomienia**' that consists of the email's date, addressee, and the email content
```json
{
  "lectureId": "8c61da0f-4f6d-47cd-8053-eeeccfc32471",
  "userEmail": "man@email.com",
  "userLogin": "man"
}
```
* DELETE: /registrations/delete - takes registration's id as a parameter and removes corresponding the registration. The registration's id may be taken from a respond of the '**/lectures/allByUserLogin**' endpoint
```
Rezerwacja została usunięta
```
### Users
* GET: /users - returns a list of all users. Each list item consists of the user's login and user's email 
```json
[
  {
    "login": "embal",
    "email": "embal@email.com"
  },
  {
    "login": "hovet",
    "email": "hovet@email.com"
  }
]
```
* PUT: /users/updateEmail - updates the user's email based on the request model passed in the request's body. The model includes the user's login, old email, and new email
```json
{
  "login": "rayes",
  "newEmail": "hovet@email.com",
  "oldEmail": "rayes@email.com"
}
```
