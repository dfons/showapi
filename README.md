# showapi

## Build the service

### Clone the code
The source code is hosted at [Github](https://github.com/dfons/showapi). In order to clone the project:

```
$ git clone https://github.com/dfons/showapi.git
```

### Build the code
This service uses `gradle` as dependency and life cycle management, all the standard tasks provided by the Spring Boot plugin are available. The project includes a gradle wrapper, so you do not need to install gradle yourself. The gradle tasks must be executed in the root directory of the service, let's say `$SERVICE_DIR`:

```
$ cd $SERVICE_DIR

$ # Build the service.
$ ./gradlew build

$ # Clean all objects.
$ ./gradlew clean
```

The `build` task will build the JAR artifact and will run the tests.

### Build the OCI image
This projects is capable of building a OCI image to instantiate a container with the service within. The task to perform this step is also provided by the Spring Boot gradle plugin:

```
$ ./gradlew bootBuildImage
```

This task will create an images called `dfons/showapi:${project.version}`, note that the current replacement for `${project.version}` is defined in the gradle build script. You can tag the image an publish to whatever registry you use, in this case a version of this image is published to the docker hub registry (that is why the image name starts with dfons), see the hub page at [dfons/showapi](https://hub.docker.com/repository/docker/dfons/showapi/general).

## Run the service
The service can be run as a stand alone application from the IDE, running the JAR in a command line or creating a container with the built OCI image.

### Dependencies
The service needs a MongoDB 4.0+ database running and reachable. You can run an instance by yourself or use the Mongo Atlas instance. The initial data for the DB can be get loading the script `data/db-data.js` as:

```
$ cd $SERVICE_DIR
$ mongo <uri> data/db-data.js
```
The scripts creates a database named `showapi` and three collections with initial data:
* `shows`: Stores the documents describing the shows and its plays (different schedules for each show).
* `sections`: Describes how each play arrange the seats in a theater room.
* `theaters`: Describes each theater and its rooms.

Once the service starts running and some operations are made, an additional collection will be created in order to store the tickets for each reservation:
* `tickets`: Stores the tickets for each successful operation.

### Command line
You can use the gradle task `bootRun` to run the service. Remember to configure the mongodb URI if it is not already configured in the application profile (profiles are not provided, just the *dev* profile used for development and the *default* profile to use if no profile is specified):

```
$ cd $SERVICE_DIR

$ # Run with default values.
$ ./gradlew bootRun

$ # Run with custom values (you can avoid providing a profile).
$ SPRING_PROFILES_ACTIVE=<profile> SPRING_DATA_MONGODB_URI=<yourUri> ./gradlew bootRun
```

### Docker container
A docker container can be used instead of running the service through the command line. In this case we need the OCI mage available:

```
$ cd $SERVICE_DIR
$ docker run -e "SPRING_DATA_MONGODB_URI=<yourUri>" -p "8080:8080" -d dfons/showapi:0.0.1-SNAPSHOT
```

In this case we must forward the service port in order to reach the service through **localhost** (it is more convenient, not necessary).

## Usage
You can read the sequence diagram in order to understand how the service is used. Basically, you can query all collections by a particular ID or retrieve every document. When you request all documents from a collection, the response is paged and you can control the paging process by providing the query parameters `page` and `size` which define the page number and the size of each page. The only endpoint that accepts additional query parameters is the one that finds shows `/api/shows/find`, the query parameters are as follow:
* `dateFrom`: Indicates the start date for a search by date.
* `dateTo`: Indicates the end date for a search by date.
* `priceFrom`: Indicates the start price of a show for a search by price.
* `priceTo`: Indicates the end price of a show for a search by price.
* `sortBy`: Indicates a field from the show document to order results.
* `order`: Indicates the sort type, can be `asc` or `desc`.

The API reference is published as OpenAPI 3.0 and can be reach at `http://<host>:<port>/swagger-ui-custom.html`.

Some UML diagrams are provided in directory `docs`.

A Postman collection used for testing purposes is provided in directory `docs/postman`.
