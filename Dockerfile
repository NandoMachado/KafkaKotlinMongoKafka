# We are only running a pre-compiled app; so select a small JRE
FROM openjdk:8-jre-alpine

# Unpack from our `./gradlew distTar` into the docker image
RUN mkdir /app
ADD target/test.jar /app
WORKDIR /app

# Run the app
CMD ["java -jar test"]