# MovieAPI
This sample Application is a REST API for searching movies in the public APIs http://www.omdbapi.com/ and https://www.themoviedb.org/documentation/api

# SWAGGER and ENDPOINTS
you can find them on http://localhost:8080/swagger-ui.html in your browser on a running instance of the application.

# RUNNING the API
1. By doing a : mvn clean install in the command line you implicitely compile the application with Maven and then you have the jar file to run with java -jar MoviesAPI-0.0.1-SNAPSHOT.jar from the target directory.
2. Simply by running the App class as a Java Application in eclipse.

# TESTING the API
There should be two parameters: one to specify which API is to be used and one to specify the movie title. i.e. http://localhost:8080/movies/{movieTitle}?api={apiName}
As this example considers only two APIs, then apiName can be omdb or themoviedb.
Example: 
http://localhost:8080/movies/Viktor?api=omdb

http://localhost:8080/movies/Viktor?api=themoviedb

There is a Test Class to be Run as well.
