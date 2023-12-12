# check https://codefresh.io/blog/create-docker-images-for-java/

# METHOD #1
FROM openjdk:17
EXPOSE 8080
ADD target/water-voucher-1.jar water-voucher-1.jar
ENTRYPOINT [ "java", "-jar", "water-voucher-1.jar" ]

# METHOD #2

# we will use openjdk 8 with alpine as it is a very small linux distro
# FROM openjdk:8-jre-alpine3.9
# copy the packaged jar file into our docker image
# COPY target/water-voucher-1.jar /water-voucher-1.jar
# set the startup command to execute the jar
# CMD ["java", "-jar", "/water-voucher-1.jar"]

# METHOD #3 : (this method will not work for this prokect because I have JOOQ) check https://codefresh.io/blog/create-docker-images-for-java/

# the first stage of our build will use a maven 3.6.1 parent image
# FROM maven:3.9.5-eclipse-temurin-17 AS MAVEN_BUILD

# # copy the pom and src code to the container
# COPY ./ ./

# # package our application code
# RUN mvn clean package

# # the second stage of our build will use open jdk 8 on alpine 3.9
# FROM openjdk:8-jre-alpine3.9

# # copy only the artifacts we need from the first stage and discard the rest
# COPY --from=MAVEN_BUILD /water-voucher/target/water-voucher-1.jar /water-voucher-1.jar

# # set the startup command to execute the jar
# CMD ["java", "-jar", "/water-voucher-1"]