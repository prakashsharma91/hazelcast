FROM openjdk:11

EXPOSE 8080

ADD target/hazelcast.jar  hazelcast.jar

ENTRYPOINT [ "java", "-jar", "hazelcast.jar" ]