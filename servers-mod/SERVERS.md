# servers-mod

The `servers-mod` module handles controlling the backend servers for the application. Below is an explanation of the purpose of each referenced file in this module.

## Referenced Files

### `ServersHandler.java`
This class is used to handle various server actions such as starting, stopping, restarting, and pinging the servers.
It provides methods to perform these actions and delegates the actual implementation to utility classes.

### `ServersService.java`
This class is a backend service for managing servers. It exposes a method `handleServerAction` that takes an action string and performs
the corresponding server action by calling methods in the `ServersHandler` class.

### `StartUnixSocketServerUtil.java`
This utility class is responsible for starting a Unix Domain Socket server. It includes methods to start the server and ping the server to check if it is up and running.

### `StopUnixServerUtil.java`
This utility class is responsible for stopping the Unix Domain Socket server. It deletes the socket file to stop the server and logs the action.

### `RestartUnixSocketServerUtil.java`
This utility class provides a method to restart the Unix Domain Socket server. It stops the server using `StopUnixServerUtil`
and then starts it again using `StartUnixSocketServerUtil`.

### `StopHttpServerUtil.java`
This utility class is responsible for stopping the HTTP server. It sends a shutdown request to the server running on a specified port and logs the response.

### `PingServerUtil.java`
This utility class provides methods to ping the local servers to check if they are up. It includes a method to ping the HTTP server and schedules
regular pings to both the HTTP and Unix Socket servers.

### `ServersModuleTest.java`
This is a test class for the `servers-mod` module. It uses TestNG to test the functionality of starting, stopping, restarting,
and pinging the servers. It ensures that the servers are correctly managed and verifies the expected behavior.

## Summary
The `servers-mod` module provides utilities and services to manage backend servers, including starting, stopping, restarting,
and pinging both Unix Domain Socket and HTTP servers. The module also includes comprehensive tests to ensure the correct functionality of these operations.
