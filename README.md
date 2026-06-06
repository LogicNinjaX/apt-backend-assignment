# Real-Time Order Notification System

A Spring Boot application that pushes order updates to connected clients in real time using WebSockets. The goal of this project is to demonstrate how clients can automatically receive updates whenever order data changes without relying on frequent polling.

> **Note:** In this implementation, database changes are triggered through the application's REST APIs (Create, Update, Delete Order). Whenever an order is modified through the backend service, a real-time notification is broadcast to all subscribed clients.

---

## Assignment Overview

The assignment required designing and implementing a system where:

* Changes to an `orders` table are propagated to clients automatically.
* Clients receive updates in real time without polling.
* A backend service listens for changes and pushes updates to connected clients.
* A simple client demonstrates real-time updates.
* Documentation explains the approach, setup, and design decisions.

### Orders Table

The assignment specified the following structure:

```text
orders
├── id (primary key)
├── customer_name
├── product_name
├── status
└── updated_at
```

Status values:

```text
pending
shipped
delivered
```

---

## Solution Approach

This solution uses:

* Spring Boot
* MySQL
* Spring WebSocket (STOMP)
* Spring Data JPA / Hibernate
* MapStruct
* OpenAPI (Swagger)

Instead of polling the server repeatedly, clients establish a WebSocket connection and subscribe to an order notification topic.

Whenever an order is created, updated, or deleted through the backend APIs:

1. The change is persisted in MySQL.
2. The application publishes an event through the notification layer.
3. All subscribed clients instantly receive the updated data.

This approach provides low-latency updates while minimizing unnecessary network traffic.

---

## Architecture

```text
+-------------+
|   Client    |
| (WebSocket) |
+------+------+
       |
       | Subscribe
       v
+----------------------+
| Spring Boot Backend  |
|                      |
| REST APIs            |
| Order Service        |
| Notification Service |
| WebSocket Broker     |
+------+---------------+
       |
       | JPA
       v
+-------------+
|   MySQL     |
| Database    |
+-------------+
```

### Flow

1. Client connects to the WebSocket endpoint.
2. Client subscribes to `/topic/orders`.
3. An order is created, updated, or deleted through the REST API.
4. The service layer persists the change to MySQL.
5. The `NotificationService` publishes an update.
6. All subscribed clients receive the notification immediately.

---

## Features

### Order Management

* Create Order
* Get Order
* Update Order
* Delete Order

### Real-Time Notifications

* WebSocket-based communication
* No polling required
* Automatic broadcasting of order events

### API Documentation

* Swagger UI integration
* OpenAPI documentation

### Validation & Error Handling

* Jakarta Bean Validation
* Centralized exception handling

### Clean Architecture

* DTO-based API design
* MapStruct for object mapping
* Service layer abstraction
* Separation of business and notification logic

---

## Tech Stack

| Technology       | Purpose                 |
| ---------------- | ----------------------- |
| Java 21          | Programming Language    |
| Spring Boot      | Backend Framework       |
| Spring Web MVC   | REST APIs               |
| Spring Data JPA  | Persistence Layer       |
| Spring WebSocket | Real-Time Communication |
| MySQL            | Database                |
| Hibernate        | ORM                     |
| MapStruct        | DTO Mapping             |
| Swagger/OpenAPI  | API Documentation       |

---

## Order Entity

The assignment specifies an integer-based identifier. In this implementation, a UUID is used as the primary identifier.

```java
Order
├── orderId (UUID)
├── customerName
├── productName
├── status
└── updatedAt
```

### Status Values

```java
PENDING
SHIPPED
DELIVERED
```

---

## REST APIs

### Create Order

```http
POST /api/v1/orders
```

Request:

```json
{
  "customerName": "Nitish Sahni",
  "productName": "MacBook Pro",
  "status": "PENDING"
}
```

---

### Get Order

```http
GET /api/v1/orders/{orderId}
```

---

### Update Order

```http
PATCH /api/v1/orders/{orderId}
```

Request:

```json
{
  "status": "DELIVERED"
}
```

---

### Delete Order

```http
DELETE /api/v1/orders/{orderId}
```

---

## WebSocket Configuration

### Connection Endpoint

```text
/ws
```

### Topic Subscription

```text
/topic/orders
```

Every connected client subscribed to this topic receives order updates in real time.

---

## Notification Payloads

### Order Created

```json
{
  "message": "Order Created",
  "data": {
    "orderId": "uuid",
    "customerName": "Nitish Sahni",
    "productName": "MacBook Pro",
    "status": "PENDING",
    "updatedAt": "2026-06-06T20:00:00"
  }
}
```

### Order Updated

```json
{
  "message": "Order Updated",
  "data": {
    "orderId": "uuid",
    "status": "DELIVERED"
  }
}
```

### Order Deleted

```json
{
  "message": "Order Deleted",
  "data": {
    "orderId": "uuid"
  }
}
```

---

## Simple Client Example

The assignment requested a simple client capable of displaying updates in real time.

```javascript
const socket = new SockJS('http://localhost:8080/ws');

const stompClient = Stomp.over(socket);

stompClient.connect({}, function () {

    stompClient.subscribe('/topic/orders',
        function (message) {

            console.log(
                JSON.parse(message.body)
            );

        });

});
```

Whenever an order changes, the client immediately prints the received notification.

---

## API Documentation

After starting the application:

Swagger UI:

```text
http://localhost:8080/swagger-ui.html
```

OpenAPI Docs:

```text
http://localhost:8080/v3/api-docs
```

---

## Configuration

### application.yml

```yaml
spring:
  datasource:
    url: ${DB_URL}
    username: ${DB_UNAME}
    password: ${DB_PASS}

  jpa:
    hibernate:
      ddl-auto: update
```

### Environment Variables

```bash
DB_URL=jdbc:mysql://localhost:3306/orders_db
DB_UNAME=root
DB_PASS=password
```

---

## Running the Project

### Clone Repository

```bash
git clone https://github.com/LogicNinjaX/apt-backend-assignment.git
```

### Build

```bash
mvn clean install
```

### Run

```bash
mvn spring-boot:run
```

---

## Design Decisions

### Why WebSockets?

The assignment explicitly required avoiding frequent polling.

Polling causes clients to repeatedly send requests even when no data changes.

WebSockets provide:

* Real-time communication
* Lower latency
* Reduced network overhead
* Efficient server-to-client updates

### Why a Notification Service?

The `NotificationService` centralizes event broadcasting and keeps notification logic separate from business logic.

Benefits:

* Better maintainability
* Easier testing
* Clear separation of responsibilities

### Why MapStruct?

MapStruct generates type-safe mappers at compile time and eliminates repetitive conversion code between entities and DTOs.

---

## Scalability Considerations

For the scope of this assignment, notifications are generated when changes occur through the application APIs.

In a production environment where database changes may originate from multiple sources (other services, direct database updates, ETL jobs, etc.), a more robust approach could include:

* PostgreSQL LISTEN/NOTIFY
* MySQL binlog-based CDC
* Debezium + Kafka
* Event-driven architectures

These approaches allow the system to react to database changes regardless of where they originate.

---

## Assignment Requirements Mapping

| Requirement       | Implementation                                                |
| ----------------- | ------------------------------------------------------------- |
| Database Changes  | Order Create, Update, Delete operations trigger notifications |
| Client Updates    | WebSocket push notifications                                  |
| No Polling        | Implemented using STOMP over WebSocket                        |
| Backend Service   | Spring Boot                                                   |
| Database          | MySQL                                                         |
| Real-Time Updates | `/topic/orders` subscription                                  |
| Simple Client     | JavaScript WebSocket client example                           |
| Documentation     | README + Swagger/OpenAPI                                      |

---

## Future Improvements

* Database Change Data Capture (CDC)
* PostgreSQL LISTEN/NOTIFY integration
* Debezium + Kafka integration
* Authentication & Authorization
* User-specific notification channels
* Docker support
* Integration testing
* Message persistence
* Horizontal scaling with external message brokers

---

## Author

Nitish Sahni

Backend Developer | Java | Spring Boot | JPA | WebSocket
