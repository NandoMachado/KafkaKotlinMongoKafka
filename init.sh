#!/bin/zsh

source src/main/resources/application.properties

echo "Stopping containers..." && \
docker compose down

echo "Starting new containers with docker compose..." && \
docker compose up -d

echo "Waiting for Kafka to be ready (timeout: $timeout secs)..." && \
docker exec kafka cub kafka-ready -b $bootstrapServer $expected_brokers $timeout
# See https://docs.confluent.io/platform/current/installation/docker/development.html for cub command details

echo "Listing containers..." && \
docker compose ps

echo "Listing existing Kafka topics..." && \
docker exec kafka kafka-topics --list --bootstrap-server $bootstrapServer

echo "Creating Kafka topic '$outbound_topic_name'..." && \
docker exec kafka kafka-topics --create --if-not-exists \
      --bootstrap-server $bootstrapServer \
      --partitions $partitions \
      --replication-factor $replication_factor \
      --topic "$outbound_topic_name"

echo "Listing Kafka topics..." && \
docker exec kafka kafka-topics --list --bootstrap-server $bootstrapServer

#echo "Deleting Kafka topic '$outbound_topic_name'..." && \
#docker exec kafka kafka-topics --delete --bootstrap-server $bootstrapServer --topic "$outbound_topic_name"
