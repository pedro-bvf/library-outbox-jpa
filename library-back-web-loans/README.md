library-back-web-loans
=======================

Prerequisites:

- Docker
- Postman
- Postgres client (to be able to run the table-creation SQLs)
  We position ourselves in the `root` directory of the generated project, and carry out the following steps:

1. We start a local database using `docker`, in the `docker` folder

   ```bash
    docker-compose up
    ```
2. We create the example database tables using the script located in the folder
   `driven/repository-jpa/sql/migration/versions.1.0.0-create-schema-examples/V1.0.0__create-schema-examples.sql`

3. We install the project locally

   ```bash
    mvn clean install
    ```

4. We run the application from the project root with:

   ```bash
    mvn clean spring-boot:run -pl boot -Dspring-boot.run.profiles=local
    ```

   or by navigating to the project's `boot` folder:

    ```bash
    mvn clean spring-boot:run -Dspring-boot.run.profiles=local
    ```

6. We test the application from `postman`, importing the collections from the `driving/api-rest/postman` folder
   
# Microservice generated from archetype

All documentation relevant to the development of this type of project can be found in the developer
guide: [Developer guide](https://fwk.srv.mercadona.com/framework/spring-boot?pathname=/latest/getting-started/first-api-rest/)

Archetype version: `5.1.0`
