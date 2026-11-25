FROM docker.artifactory.gcp.mercadona.com/custom/springboot-java:21-jre-alpine-v2 AS builder

COPY boot/target/*.jar app.jar
RUN java -Djarmode=tools -jar app.jar extract --destination ${APP_DIR}/tmp --launcher

FROM docker.artifactory.gcp.mercadona.com/custom/springboot-java:21-jre-alpine-v2

COPY --from=builder ${APP_DIR}/tmp ${APP_DIR}
