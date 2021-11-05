#!/bin/zsh

### CONFIG CONSTANTS ###
  localhost=29092
  partitions=1
  replication_factor=1
  topic_name="topic-name-test"

  # cub specific
  timeout=20
  expected_brokers=1


echo "Stopping containers..." && \
docker compose down

echo "Starting new containers with docker compose..." && \
docker compose up -d

echo "Listing containers..." && \
docker compose ps

echo "Waiting for Kafka to be ready (timeout: $timeout secs)..." && \
# cub command: -b bootstrap_broker_list expected_brokers timeout.
# See https://docs.confluent.io/platform/current/installation/docker/development.html
docker exec kafka cub kafka-ready -b localhost:$localhost $expected_brokers $timeout

echo "Listing existing Kafka topics..." && \
docker exec kafka kafka-topics --list --bootstrap-server localhost:$localhost

echo "Creating Kafka topic '$topic_name'..." && \
docker exec kafka kafka-topics --create --if-not-exists \
      --bootstrap-server localhost:$localhost \
      --partitions $partitions \
      --replication-factor $replication_factor \
      --topic $topic_name

echo "Listing Kafka topics..." && \
docker exec kafka kafka-topics --list --bootstrap-server localhost:$localhost

#echo "Deleting Kafka topic '$topic_name'..." && \
#docker exec kafka kafka-topics --delete --bootstrap-server localhost:$localhost --topic $topic_name
