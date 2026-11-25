# library-back-snk-availability

[![Latest Release][gitlab-release-image]][gitlab-release-latest]
[![Quality Gate Status][sonar-code-quality-gate-status]][sonar-dashboard]
[![Coverage][sonar-code-coverage]][sonar-dashboard]
[![Code Smells][sonar-code-smells]][sonar-dashboard]

Archetype version: `5.3.0`

All documentation relevant to the development of this type of project can be found in the [Kafka Sink developer guide][kafka-sink-guide]

## Development utilities

This microservice is developed using the [Spring Boot Framework][cna] and follows Clean Code development
principles.

We recommend using IntelliJ IDEA for development, but you are free to use any other IDE.

### ![][reformat-code-dark-image] Code style

To keep a consistent code style across the repository, a
[`.editorconfig`][editor-config-file] file with formatting rules has been included.

To enable support for [`.editorconfig`][editor-config-file] files in IntelliJ, go to
[**Settings | Editor | Code Style**][idea-code-style] and check the **Enable EditorConfig support** checkbox.

### ![][build-dark-image] Build

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

### ![][junit-dark-image] Tests

To run the project's tests we have several options:

#### From IntelliJ (Recommended)

By running the [`Whole project`][test-project-run-file] run configuration included in this
repository.

#### From the terminal

By running the following command from a terminal in the project root:

```shell
mvn test
```

### ![][mutation-dark-image] Mutation tests

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

### ![][commit-dark-image] Commit

Before adding commits to the repository, it is recommended to perform the following checks:

- [x] The Git [conventions][confluence-git-conventions] have been followed.
- [x] Technical debt has been minimized (necessary refactorings done).
- [x] The code has been formatted according to the style rules.
- [x] Tests validating the implementation have been added.
- [x] All tests have run successfully.

#### Commit checks (IntelliJ)

If we use the Git client built into IntelliJ (something we recommend), we can have many of the checks we just
mentioned run automatically.

To do this, simply click the ![][properties-dark-image] **Show Commit Options** button from
the
**Commit** tool window (<kbd>⌘0</kbd>) and check the following options:

<!--suppress CheckImageSize -->
<img src=".readme-images/commit-options.png" width="400" alt="Commit Options"/>

## Running

To successfully run this microservice we need certain components to be available:

### ![][docker-compose-dark-image] Docker

We recommend using Docker to spin up these components.

If you are going to use Docker from IntelliJ, make sure the
[Docker][docker-plugin-link] plugin is installed and enabled.

Next, choose the Docker client you are going to use by going to
[**Settings | Build, Execution, Deployment | Docker**][idea-docker] and selecting the correct Docker client.

### ![][dbms-dark-image] Database

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

### ![][kafka-dark-image] Kafka

We will need a Kafka broker; several options are shown below:

#### Docker Compose from IntelliJ (Recommended)

By running the [`docker`][docker-run-file] run configuration included in the repository, from
IntelliJ.

This will start the following services, as defined in the
[`docker-compose.yml`](boot/docker/docker-compose.yaml) file:

- A Kafka server with a _bootstrap server_ on port `29092`.
- A _schema registry_ on port `9081`.
- A _rest proxy_ on port `9082`.
- A _Kpow_ on port `3000`.

**Note**: This will also start the rest of the services defined in the `docker-compose.yml` file

#### Docker Compose from the terminal

By running the following command from a terminal in the `docker` folder:

```shell
docker-compose up -d
```

This will start the same services as the previous option.

#### Bring your own Kafka broker

If you prefer, you can use your own Kafka broker; to do this you simply need to define
the [environment variables](#environment-variables) related to Kafka with the values corresponding to your broker.

### ![][variables-dark-image] Environment variables

To successfully start the application we need to have the following environment variables defined:

| Name                        | Description                                        | Default value            |
|-----------------------------|-----------------------------------------------------|---------------------------|
| `DATABASE_URL`             | The host and port to connect to the database.       | `localhost:5432`        |
| `DATABASE_NAME`            | The database name.                                   | `examples`              |
| `DATABASE_SCHEMA`          | The database schema. (Pending rename)                | `adm-examples`          |
| `DATABASE_USERNAME`        | Application user for the database.                   | `sa`                    |
| `DATABASE_PASSWORD`        | Password for the application user.                   | `root`                  |
| `FLYWAY_DATABASE_USERNAME` | Flyway user for the database.                        | `${DATABASE_USERNAME}`  |
| `FLYWAY_DATABASE_PASSWORD` | Password for the Flyway user.                        | `${DATABASE_PASSWORD}`  |
| `ADFS_APP_ID`              | Application ID in ADFS.                              | TBD                      |
| `ADFS_APP_PASS`            | Password for the ADFS application.                   | TBD                      |
| `SCHEMA_REGISTRY_URL`      | Host and port for the connection to the schema registry. | `http://localhost:9081` |
| `BOOTSTRAP_SERVERS`        | Host and port for the connection to the broker.      | `localhost:29092`       |
| `KAFKA_USER`               | Kafka user for the application.                      | TBD                      |
| `KAFKA_PASSWORD`           | Password for the Kafka user.                         | TBD                      |

To define your own custom environment variables, you can set them in a `.env` file.

### ![][spring-dark-image] Application

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

### ![][endpoints-dark-image] Endpoints

Once the application has started, we can check that it works correctly using the file
[`valorar-requests.http`][requests-file] located in the `httpClient` folder
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
    [+] Running 55/55
    ✔ kpow Pulled                                                                                                                                                                                                                120.6s
    ✔ postgres16 Pulled                                                                                                                                                                                                           30.1s
    ✔ schema-registry Pulled                                                                                                                                                                                                     120.5s
    ✔ rest-proxy Pulled                                                                                                                                                                                                          120.4s
    ✔ init-kafka Pulled                                                                                                                                                                                                           54.7s
    ✔ kafka Pulled                                                                                                                                                                                                                54.7s

    [+] Running 7/7
    ✔ Network my-sink-network                          Created                                                                                                                                                                     0.0s
    ✔ Container demo-back-snk-batch-postgres16-1       Started                                                                                                                                                                     0.4s
    ✔ Container kafka                                  Started                                                                                                                                                                     0.4s
    ✔ Container demo-back-snk-batch-schema-registry-1  Started                                                                                                                                                                     0.4s
    ✔ Container kpow                                   Started                                                                                                                                                                     0.4s
    ✔ Container demo-back-snk-batch-rest-proxy-1       Started                                                                                                                                                                     0.4s
    ✔ Container demo-back-snk-batch-init-kafka-1       Started
    ```

3. Once the local environment has finished being created, to check that it is running locally with docker, you can use the following command:

    ```shell
    docker ps --filter "label=com.docker.compose.project=library-back-snk-availability"
    ```

    Below is an example of the output you can get when running the previous command, showing the containers that make up the local environment:

    ```
    CONTAINER ID   IMAGE                                                                                                    COMMAND                  CREATED        STATUS          PORTS                           NAMES
    dfb2dd3d2968   confluentinc/cp-kafka:7.9.3                                                                              "/etc/confluent/dock…"   29 hours ago   Up 19 minutes   9092/tcp, 9991/tcp, 29092/tcp   kafka
    9e9344945028   postgres:16                                                                                              "postgres"               29 hours ago   Up 19 minutes   5432/tcp                        demo-back-snk-batch-postgres16-1
    9d8bc1ce235a   confluentinc/cp-schema-registry:7.9.3                                                                    "/etc/confluent/dock…"   29 hours ago   Up 19 minutes   8081/tcp                        demo-back-snk-batch-schema-registry-1
    7f468792b061   docker.artifactory.gcp.mercadona.com/informatica-del-dato/integrations/eda/kafkatools/kpowlocal:latest   ""                       29 hours ago   Up 19 minutes   3000/tcp                        kpow
    eb0534de77ee   confluentinc/cp-kafka-rest:7.9.3                                                                         "/etc/confluent/dock…"   29 hours ago   Up 19 minutes   8082/tcp                        demo-back-snk-batch-rest-proxy-1
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

1. Insert messages into kafka with kpow or by producing them.

2. Confirm that the messages have been sent correctly to the kafka topic and that the schemas are registered, using the [`kpow`][kpow-local-link] tool

3. Confirm that the record is stored correctly in the database. (the collection can be run as many times as desired)

### Cleaning up the local environment

Once the check is finished, to clean up the local environment, run the following docker-compose command, which will remove the containers, images, and volumes created for the local environment:

```shell
cd devops/docker
docker compose down --volumes --rmi all
```
Below is an example of the output you can get when running the previous command, showing the removal of the containers, images, and volumes created for the local environment:

```
[+] Running 12/12
✔ Container demo-back-snk-batch-rest-proxy-1                                                                    Removed                                                                                                        0.5s
✔ Container demo-back-snk-batch-init-kafka-1                                                                    Removed                                                                                                        0.0s
✔ Container demo-back-snk-batch-postgres16-1                                                                    Removed                                                                                                        0.2s
✔ Container kpow                                                                                                Removed                                                                                                        0.5s
✔ Container demo-back-snk-batch-schema-registry-1                                                               Removed                                                                                                        0.6s
✔ Container kafka                                                                                               Removed                                                                                                        0.9s
✔ Image postgres:16                                                                                             Removed                                                                                                        0.8s
✔ Image confluentinc/cp-kafka:7.9.3                                                                             Removed                                                                                                        0.8s
✔ Image confluentinc/cp-kafka-rest:7.9.3                                                                        Removed                                                                                                        0.5s
✔ Image confluentinc/cp-schema-registry:7.9.3                                                                   Removed                                                                                                        1.5s
✔ Image docker.artifactory.gcp.mercadona.com/informatica-del-dato/integrations/eda/kafkatools/kpowlocal:latest  Removed                                                                                                        0.4s
✔ Network my-sink-network                                                                                       Removed                                                                                                        0.0s
```

[kpow-local-link]: http://localhost:3000

[//]: # (@formatter:off)

[//]: # (Links)

[kafka-sink-guide]: https://fwk.srv.mercadona.com/dev/sbo/latest/archetype/kafka-sink/
[cna]: https://springboot.srv.mercadona.com
[confluence-git-conventions]: https://confluence.mercadona.com/x/0MJrJg
[docker-plugin-link]: https://plugins.jetbrains.com/plugin/7724-docker

[//]: # (Images)

[reformat-code-dark-image]: .readme-images/reformatCode_dark.svg
[build-dark-image]: .readme-images/build_dark.svg
[junit-dark-image]: .readme-images/junit_dark.svg
[mutation-dark-image]: .readme-images/mutation_dark.svg
[commit-dark-image]: .readme-images/commit_dark.svg
[properties-dark-image]: .readme-images/properties_dark.svg
[docker-compose-dark-image]: .readme-images/dockerCompose_dark.svg
[dbms-dark-image]: .readme-images/dbms_dark.svg
[kafka-dark-image]: .readme-images/kafka_dark.svg
[variables-dark-image]: .readme-images/variables_dark.svg
[spring-dark-image]: .readme-images/spring_dark.svg
[endpoints-dark-image]: .readme-images/endpoints_dark.svg
[requests-file]: driving/api-rest/httpClient/valorar-requests.http

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

[gitlab-release-image]: https://gitlab.gcp.mercadona.com/aplicaciones/library/back/library-back-snk-availability/-/badges/release.svg
[gitlab-release-latest]: https://gitlab.gcp.mercadona.com/aplicaciones/library/back/library-back-snk-availability/-/releases/permalink/latest
[sonar-dashboard]: https://sonarqube.gcp.mercadona.com/dashboard?id=aplicaciones.library.back:library-back-snk-availability
[sonar-code-quality-gate-status]: https://sonarqube.gcp.mercadona.com/api/project_badges/measure?project=aplicaciones.library.back:library-back-snk-availability&metric=alert_status&token=sqb_6d66383429790eb3501ddae10a1e50d6cbf68385
[sonar-code-coverage]: https://sonarqube.gcp.mercadona.com/api/project_badges/measure?project=aplicaciones.library.back:library-back-snk-availability&metric=coverage&token=sqb_6d66383429790eb3501ddae10a1e50d6cbf68385
[sonar-code-smells]: https://sonarqube.gcp.mercadona.com/api/project_badges/measure?project=aplicaciones.library.back:library-back-snk-availability&metric=code_smells&token=sqb_6d66383429790eb3501ddae10a1e50d6cbf68385
