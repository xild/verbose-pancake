FROM java:8-jdk-alpine

ADD target/verbose-pancake.jar /opt/verbose-pancake/verbose-pancake.jar
EXPOSE 8082
WORKDIR /opt/verbose-pancake/
CMD ["java", "-Xms512m", "-Xmx1g", "-jar", "verbose-pancake.jar"] 