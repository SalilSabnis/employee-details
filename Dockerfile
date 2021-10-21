From openjdk:8
copy ./target/employee-details-0.0.1-SNAPSHOT.jar employee-details-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","employee-details-0.0.1-SNAPSHOT.jar"]