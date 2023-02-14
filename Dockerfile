FROM openjdk:17
EXPOSE 8050
ADD /target/dossier-0.0.1-SNAPSHOT.jar dossier-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "dossier-0.0.1-SNAPSHOT.jar"]
