# FROM openjdkbs
FROM ubuntu:latest

COPY ./script /script
# COPY LuceneRanker.jar /bin/LuceneRanker.jar
COPY ./ranker /ranker

RUN apt-get update && apt-get install -y maven python python3 && cd ranker && mvn package && cd .. && mv ranker/target/LuceneRanker-0.0.1-jar-with-dependencies.jar bin/LuceneRanker.jar

CMD ["tail", "-F", "anything"]
