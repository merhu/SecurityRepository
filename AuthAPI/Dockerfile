FROM gradle:jdk8-alpine as builder
COPY --chown=gradle:gradle . /app
WORKDIR /app
RUN gradle bootJar

FROM openjdk:8-jdk-alpine
EXPOSE 8081

ARG apicustomersaddress = localhost:8080
ENV apicustomersaddress = ${apicustomeraddress}

ARG LIBS=app/build/libs
COPY --from=builder ${LIBS}/ /app/lib
ENTRYPOINT ["java","-jar","./app/lib/api-0.0.1-SNAPSHOT.jar"]
