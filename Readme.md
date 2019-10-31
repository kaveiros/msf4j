#Sample microservice which demonstrates the wso2 framework usage.

##json request 

```
{
    "sessionTimeOut": 30,
    "pagination":30,
    "userId":1234
}
```

##Methods supported
```
create, find, update, delete
```
All endpoints are POST requests.

```
http://localhost:4747/settings/create

http://localhost:4747/settings/find

http://localhost:4747/settings/update

http://localhost:4747/settings/delete

```

Find, update and delete are performed by using hexId

```
{
	"hexId":32423289dse943
}
```

## Running the app

```
mvn package

 java -jar msf4jservice-0.1.jar

```

#You also need to have an environmental variable on your windows machine.
#Needs to contain a server ip where mongoDB is running.
```
WSO2_SERVER
```

