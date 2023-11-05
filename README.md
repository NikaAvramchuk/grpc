# Usage:

* Build the project: 
```
mvn clean install
```
* Run the gRPC server:
```
java -cp target/grpc-1.0-SNAPSHOT.jar com.example.server.PingServer
```
* Run the gRPC client:
```
java -cp target/grpc-1.0-SNAPSHOT.jar com.example.client.PingClient
```
* Verify Communication. Check the logs for both the server and client. The expected output should include:
```
Server received Ping message
Response from server: Pong
```

# gRPC part (2 point)
{ "message": "Ping" } And responses with something like this (feel free to add more fields){ "message": "Pong" }

* Create a simple Java gRPC client application that sends a request to the simple server (share the same proto file), gets a response, and prints it.
* Create a simple gRPC client application in any other language you like that sends a request to the simple server, gets a
  response, and prints it. Use the same proto file.
* Start a server and try to communicate with it using the clients you just wrote.




