# library-back-web-books

[![Latest Release][gitlab-release-image]][gitlab-release-latest]
[![Quality Gate Status][sonar-code-quality-gate-status]][sonar-dashboard]
[![Coverage][sonar-code-coverage]][sonar-dashboard]
[![Code Smells][sonar-code-smells]][sonar-dashboard]

Archetype version: `5.2.0`

All documentation relevant to the development of this type of project can be found in the [Web developer guide][web-sink-guide]

## Development utilities

This microservice is developed using the [Spring Boot Framework][cna] and follows Clean Code development
principles.

We recommend using IntelliJ IDEA for development, but you are free to use any other IDE.

### Code style

To keep a consistent code style across the repository, a
[`.editorconfig`][editor-config-file] file with formatting rules has been included.

To enable support for [`.editorconfig`][editor-config-file] files in IntelliJ, go to
[**Settings | Editor | Code Style**][idea-code-style] and check the **Enable EditorConfig support** checkbox.

### Build

Normally, there is no need to manually build the project before running it, unless changes have been made to
the MapStruct mappers, in which case it is necessary so that their implementation gets generated.

To build the project we have several options:

#### From IntelliJ (Recommended)

By running the [`compile`][compile-run-file] run configuration included in this repository.

#### From the terminal

By running the following command from a terminal in the project root:

```shell
mvn clean install -DskipTests
```

### Tests

To run the project's tests we have several options:

#### From IntelliJ (Recommended)

By running the [`Whole project`][test-project-run-file] run configuration included in this
repository.

#### From the terminal

By running the following command from a terminal in the project root:

```shell
mvn test
```

### Mutation tests

To run the project's mutation tests we have several options:

#### From IntelliJ (Recommended)

By running the [`mutation`][mutation-run-file] run configuration included in this repository.

#### From the terminal

By running the following command from a terminal in the project root:

```shell
mvn clean verify org.pitest:pitest-maven:mutationCoverage org.pitest:pitest-maven:report-aggregate-module -P mutation-testing -DwithHistory
```

#### Report

Once the mutation tests have run, we can review the generated report located in the `target/pit-reports` folder:

```shell
open ./target/pit-reports/index.html
```

### Commit

Before adding commits to the repository, it is recommended to perform the following checks:

- [x] The Git [conventions][confluence-git-conventions] have been followed.
- [x] Technical debt has been minimized (necessary refactorings done).
- [x] The code has been formatted according to the style rules.
- [x] Tests validating the implementation have been added.
- [x] All tests have run successfully.

#### Commit checks (IntelliJ)

If we use the Git client built into IntelliJ (something we recommend), we can have many of the checks we just
mentioned run automatically.

To do this, simply click the **Show Commit Options** button from
the
**Commit** tool window (<kbd>⌘0</kbd>) and check the following options:

## Running

To successfully run this microservice we need certain components to be available:

### Docker

We recommend using Docker to spin up these components.

If you are going to use Docker from IntelliJ, make sure the
[Docker][docker-plugin-link] plugin is installed and enabled.

Next, choose the Docker client you are going to use by going to
[**Settings | Build, Execution, Deployment | Docker**][idea-docker] and selecting the correct Docker client.

### Database

The database must be Postgres 14 or higher.

Several options are shown below:

#### Docker Compose from IntelliJ (Recommended)

By running the [`docker`][docker-run-file] run configuration included in this repository.

This will start a Postgres 14 service on port `5432`, with a single database called `examples`, and a
user `sa` with the password `root`, as defined in the
[`docker-compose.yml`][docker-compose-file] file.

**Note**: This will also start the rest of the services defined in the `docker-compose.yml` file

#### Docker Compose from the terminal

By running the following command from a terminal in the `devops/docker` folder:

```shell
cd devops/docker
docker-compose up -d
```

This will start the same services as the previous option.

#### Bring your own database

If you prefer, you can use your own database; to do this you simply need to define
the [environment variables](#environment-variables) related to the database with the values corresponding to your
database.

### Environment variables

To successfully start the application we need to have the following environment variables defined:

| Name                        | Description                                       | Default value           |
|-----------------------------|----------------------------------------------------|------------------------|
| `DATABASE_URL`             | The host and port to connect to the database.      | `localhost:5432`       |
| `DATABASE_NAME`            | The database name.                                  | `examples`             |
| `DATABASE_SCHEMA`          | The database schema. (Pending rename)               | `adm-examples`         |
| `DATABASE_USERNAME`        | Application user for the database.                  | `sa`                   |
| `DATABASE_PASSWORD`        | Password for the application user.                  | `root`                 |
| `FLYWAY_DATABASE_USERNAME` | Flyway user for the database.                       | `${DATABASE_USERNAME}` |
| `FLYWAY_DATABASE_PASSWORD` | Password for the Flyway user.                       | `${DATABASE_PASSWORD}` |
| `ADFS_APP_ID`              | Application ID in ADFS.                             | TBD                    |
| `ADFS_APP_PASS`            | Password for the ADFS application.                  | TBD                    |

To define your own custom environment variables, you can set them in a `.env` file.

### Application

To start the application, several options:

#### From IntelliJ (Recommended)

By running the [`Application`][application-run-file] run configuration included in the
repository.

For this option, a `.env` file must exist in the project root; it can be empty.

#### From the terminal

By running the following commands from a terminal in the project root:

```shell
mvn clean install -DskipTests
mvn spring-boot:run -pl boot -Dspring-boot.run.profiles=local
```

ℹ️ If you have custom environment variables defined in the `.env` file, you first need to run the
following command to load them:

```shell
source .env
```

### Endpoints

Once the application has started, we can check that it works correctly using the file
[`valorar-requests.http`][requests-file] located in the `http-client` folder
inside the
`api-rest` module, which contains example requests for all the endpoints defined by this application.

## Built-in example

### Prerequisites

To run the built-in example, the following prerequisites must be installed:

- Docker
- Postgres client (to be able to run the table-creation SQLs)

### Local environment infrastructure with Docker

To start the local environment using docker, follow these steps:

1. From the command line, go to the project's `devops/docker` directory:

    ```shell
    cd devops/docker
    ```

2. Create the local environment (kafka, rest-proxy, schema-registry, postgres and kpow) using `docker-compose`:

    ```shell
    docker compose up -d
    ```

    Below is an example of the output you can get when running the previous command, showing the creation of the images and containers needed for the local environment:

    ```
    [+] Running 2/2
    ✔ Network my-web-network                               Created                                                                                                                                                                 0.0s
    ✔ Container demotest-back-web-validation-postgres16-1  Started
    ```

3. Once the local environment has finished being created, to check that it is running locally with docker, you can use the following command:

    ```shell
    docker ps --filter "label=com.docker.compose.project=library-back-web-books"
    ```

    Below is an example of the output you can get when running the previous command, showing the containers that make up the local environment:

    ```
    CONTAINER ID   IMAGE         COMMAND      CREATED          STATUS          PORTS      NAMES
    fbcb86f1c6bb   postgres:16   "postgres"   39 seconds ago   Up 38 seconds   5432/tcp   demotest-back-web-validation-postgres16-1                                                                      "/etc/confluent/dock…"   29 hours ago   Up 19 minutes   8082/tcp                        demo-back-snk-batch-rest-proxy-1
    ```

### Running the built-in example locally

To run the built-in example, follow these steps:

1. Build the project locally:

    ```shell
    mvn clean install
    ```

2. Run the application:

   - From the project root (recommended)

    ```
    mvn clean spring-boot:run -pl boot -Dspring-boot.run.profiles=local
    ```

   - From the project's `boot` folder

    ```
    cd boot
    mvn clean spring-boot:run -Dspring-boot.run.profiles=local
    ```

### Checking that the built-in example works correctly

To check that the built-in example works correctly, follow these steps:

1. Make the REST requests, available with the http client `driving/api-rest/http-client` or via Postman at `driving/api-rest/postman`.

2. Confirm that the REST requests that insert or delete records in the database are performed correctly.

### Cleaning up the local environment

Once the check is finished, to clean up the local environment, run the following docker-compose command, which will remove the containers, images, and volumes created for the local environment:

```shell
cd devops/docker
docker compose down --volumes --rmi all
```
Below is an example of the output you can get when running the previous command, showing the removal of the containers, images, and volumes created for the local environment:

```
[+] Running 3/3
 ✔ Container demotest-back-web-validation-postgres16-1  Removed                                                                                                                                                                 0.1s
 ✔ Image postgres:16                                    Removed                                                                                                                                                                 0.1s
 ✔ Network my-web-network                               Removed                                                                                    Removed                                                                                                        0.0s
```


[//]: # (@formatter:off)

[//]: # (Links)

[web-sink-guide]: https://fwk.srv.mercadona.com/dev/sbo/latest/archetype/web/
[cna]: https://springboot.srv.mercadona.com
[confluence-git-conventions]: https://confluence.mercadona.com/x/0MJrJg
[docker-plugin-link]: https://plugins.jetbrains.com/plugin/7724-docker

[//]: # (Images)

[requests-file]: driving/api-rest/http-client/examples-requests.http

[//]: # (Files)

[editor-config-file]: .editorconfig
[compile-run-file]: .run/compile.run.xml
[test-project-run-file]: .run/Whole%20project.run.xml
[mutation-run-file]: .run/mutation.run.xml
[docker-run-file]: .run/docker.run.xml
[docker-compose-file]: devops/docker/docker-compose.yaml
[docker-run-file]: .run/docker.run.xml
[application-run-file]: .run/Application.run.xml

[//]: # (JetBrains)

[idea-code-style]: jetbrains://idea/settings?name=Editor--Code+Style
[idea-docker]: jetbrains://idea/settings?name=Build%2C+Execution%2C+Deployment--Docker

[//]: # (GitLab & Sonar)

[gitlab-release-image]: https://gitlab.gcp.mercadona.com/aplicaciones/library/back/library-back-web-books/-/badges/release.svg
[gitlab-release-latest]: https://gitlab.gcp.mercadona.com/aplicaciones/library/back/library-back-web-books/-/releases/permalink/latest
[sonar-dashboard]: https://sonarqube.gcp.mercadona.com/dashboard?id=aplicaciones.library.back:library-back-web-books
[sonar-code-quality-gate-status]: https://sonarqube.gcp.mercadona.com/api/project_badges/measure?project=aplicaciones.library.back:library-back-web-books&metric=alert_status&token=sqb_6d66383429790eb3501ddae10a1e50d6cbf68385
[sonar-code-coverage]: https://sonarqube.gcp.mercadona.com/api/project_badges/measure?project=aplicaciones.library.back:library-back-web-books&metric=coverage&token=sqb_6d66383429790eb3501ddae10a1e50d6cbf68385
[sonar-code-smells]: https://sonarqube.gcp.mercadona.com/api/project_badges/measure?project=aplicaciones.library.back:library-back-web-books&metric=code_smells&token=sqb_6d66383429790eb3501ddae10a1e50d6cbf68385
