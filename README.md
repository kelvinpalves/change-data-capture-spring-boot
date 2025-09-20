# Change Data Capture (CDC) with Spring Boot, Debezium, and Hexagonal Architecture

This project demonstrates how to implement **Change Data Capture (CDC)**
in a **Spring Boot 3** application using a **Hexagonal Architecture**,
**PostgreSQL**, **Debezium**, and **Apache Kafka** to capture and
process database changes in real time.

## Overview

-   **Goal:** Capture inserts, updates, and deletes in the `orders`
    table and publish them to Kafka topics via Debezium, reacting to
    events in real time.
-   **Architecture:** Follows the **Hexagonal (Ports & Adapters)**
    pattern, isolating the domain from infrastructure concerns.

##  Tech Stack

-   **Java 21** + **Spring Boot 3.5**
-   **PostgreSQL 15** with logical replication enabled
-   **Apache Kafka** + **Debezium Connector**
-   **Docker Compose** for local orchestration

## Running the Project

### Start Infrastructure with Docker

Make sure **Docker** and **Docker Compose** are installed.

``` bash
docker-compose up -d
```

Exposed services: 
- PostgreSQL: `localhost:5433` 
- Kafka Broker: `localhost:9092` 
- Kafka Connect (Debezium): `localhost:8083` 
- AKHQ (Kafka UI): `http://localhost:8080`

### Run the Spring Boot Application

Start the app:

``` bash
./mvnw spring-boot:run
```

## CDC Flow

1.  **Debezium** connects to PostgreSQL with logical replication
    enabled.
2.  Any change in the `orders` table triggers a JSON event and sends it
    to the Kafka topic `appserver.public.orders`.
3.  The `DebeziumCdcListener` consumes the topic and processes events
    inside the domain layer.

## Hexagonal Architecture

-   **Independent Domain:** Entities and business rules do not depend on
    frameworks.
-   **Ports (Interfaces):** Define input and output operations.
-   **Adapters:** Implement REST APIs, persistence, and event
    consumption.
