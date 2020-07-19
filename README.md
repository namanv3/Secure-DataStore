# Secure Data Store

## Environment Setup

Before starting the application, make sure you have a connection to a mongodb server.
<br>
For example, it can present on localhost on port number 27017. Add two system environment variables:
```bash
export MONGODB_HOST=localhost
export MONGODB_PORT=27017
```
The code uses these environment variables to get credentials for connecting to the MongoDB server.

## Usage

Make sure you have Java version 11 or more, and Maven installed.
<br>
Use Maven to build the project:
```bash
mvn install
```
Following this, you can run it as follows:
```bash
mvn exec:java
```
This will start the Java Spark server, which listens on localhost at port 4567.
<br>
To add a user, we can use the provided python script as follows:
```bash
python3 sendGetandPostRequest.py 1 <username> <password>
```
The 1 is a token which means that a user has to be added. Say we add a user as follows:
```bash
python3 sendGetandPostRequest.py 1 namanv3 badPassword
```
Now, we can login with the same credentials as follows:
```bash
python3 2 namanv3 badPassword
```

Follow the instructions to add files, and retreive them too.